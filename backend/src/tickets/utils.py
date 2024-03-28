import base64
import uuid
import datetime
from fastapi import UploadFile

from src.database.models import Ticket
from src.database.repositories import TicketRepository
from src.tickets.schemas import CreateTicketRequest, TicketStatus, AcceptTicketRequest


async def add_ticket(
    create_ticket_request: CreateTicketRequest,
    image: UploadFile,
    ticket_repository: TicketRepository,
) -> Ticket:
    id = uuid.uuid4()  # Generate random unique identifier

    ticket = Ticket(
        id=id,
        latitude=create_ticket_request.latitude,
        longitude=create_ticket_request.longitude,
        address=create_ticket_request.address,
        description=create_ticket_request.description,
        type=create_ticket_request.type,
        volume=create_ticket_request.volume,
        status=TicketStatus.NEW,
        image=base64.b64encode(image.file.read()).decode("ascii"),
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
    id: uuid.UUID, accept_ticket_request: AcceptTicketRequest, ticket_repository: TicketRepository
) -> Ticket:
    contractor_id: uuid.UUID = accept_ticket_request.contractor_id
    completion_target_date: datetime.date = accept_ticket_request.completion_target_date
    
    ticket = await ticket_repository.accept(id, contractor_id, completion_target_date)

    return ticket
