import base64
import uuid

from fastapi import UploadFile

from src.database.models import Ticket
from src.database.repositories import TicketRepository
from src.tickets.schemas import CreateTicketRequest, TicketStatus


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
    )

    await ticket_repository.add(ticket)

    return ticket
