import base64
import datetime
import logging
import uuid
import io

import aiohttp
from fastapi import UploadFile

from src.config import settings
from src.database.models import Ticket
from src.database.repositories import ContractorRepository, TicketRepository
from src.tickets.schemas import (
    AcceptTicketRequest,
    CreateTicketRequest,
    TicketStatus,
)


async def add_ticket(
    create_ticket_request: CreateTicketRequest,
    images: UploadFile,
    ticket_repository: TicketRepository,
) -> Ticket:
    id = uuid.uuid4()  # Generate random unique identifier

    original_image = images[0]
    processed_image = images[1]

    ticket = Ticket(
        id=id,
        latitude=create_ticket_request.latitude,
        longitude=create_ticket_request.longitude,
        address=create_ticket_request.address,
        description=create_ticket_request.description,
        type=create_ticket_request.type,
        volume=create_ticket_request.volume,
        status=TicketStatus.NEW,
        original_image=base64.b64encode(original_image.file.read()).decode("ascii"),
        processed_image=base64.b64encode(processed_image.file.read()).decode("ascii"),
        completion_image=None,
        completion_timestamp=None,
        contractor_id=None,
        completion_target_date=None,
    )

    await ticket_repository.add(ticket)

    return ticket


async def complete_ticket(
    id: uuid.UUID,
    image: UploadFile,
    ticket_repository: TicketRepository,
) -> Ticket:
    ticket = await ticket_repository.complete(
        id, base64.b64encode(image.file.read()).decode("ascii"), datetime.datetime.now()
    )

    return ticket


async def accept_ticket(
    id: uuid.UUID,
    accept_ticket_request: AcceptTicketRequest,
    ticket_repository: TicketRepository,
) -> Ticket:
    contractor_id: uuid.UUID = accept_ticket_request.contractor_id
    completion_target_date: datetime.date = accept_ticket_request.completion_target_date

    ticket = await ticket_repository.accept(id, contractor_id, completion_target_date)

    contractor_repository = ContractorRepository()
    contractor = await contractor_repository.get(contractor_id)
    email = contractor.email

    await send_email(email, ticket)
    return ticket


def get_html(latitude, longitude, address, date):
    return f"""
<div style="font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2">
  <div style="margin:50px auto;width:70%;padding:20px 0">
    <div style="border-bottom:1px solid #eee">
      <a href="" style="font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600"></a>
    </div>
    <p style="font-size:1.1em">Здравствуйте, это CityWatch.</p>
    <p>Вы были назначены подрядчиком работы по устранению дефекта.</p>
    <p>Координаты дефекта: ({latitude}, {longitude})</p>
    <p>Адрес: {address}</p>
    <p>Дата, до которой требуется устранить дефект: {date}</p>
    <p>Фотографии дефекта прикреплены к письму.</p>
    <!-- Button Section -->
    <div style="margin:20px 0;text-align:center;">
      <a href="http://212.60.20.177:7778/docs" style="background: #FFA500;color: #000;padding: 10px 20px;text-decoration: none;border-radius: 4px;">Перейти на сайт</a>
    </div>
    <p style="font-size:0.9em;">С уважением,<br />команда CityWatch</p>
    <hr style="border:none;border-top:1px solid #eee" />
    <div style="float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300">
      <p>AI_кабанчики</p>
    </div>
  </div>
</div>"""


async def send_email(email: str, ticket: Ticket):
    async with aiohttp.ClientSession() as session:
        # Prepare the data to be sent
        data = aiohttp.FormData()
        data.add_field("from", "Citywatch <noreply@citywatch.ru>")
        data.add_field("to", email)  # Assuming 'email' is a string
        data.add_field("subject", "Новая заявка на устранение дефекта")
        data.add_field(
            "html",
            get_html(
                latitude=ticket.latitude,
                longitude=ticket.longitude,
                address=ticket.address,
                date=ticket.completion_target_date,
            ),
        )
        # Add files
        data.add_field(
            "attachment", io.BytesIO(base64.b64decode(ticket.original_image)), filename="original_image.jpg"
        )
        data.add_field(
            "attachment", io.BytesIO(base64.b64decode(ticket.processed_image)), filename="processed_image.jpg"
        )

        # Make the POST request
        async with session.post(
            url="https://api.mailgun.net/v3/auth.papper.tech/messages",
            auth=aiohttp.BasicAuth("api", settings.mailgun_api_key),
            data=data,
        ) as response:
            # Log the response
            logging.info(await response.text())
