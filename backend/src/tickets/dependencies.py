from uuid import UUID

from fastapi import Body
from fastapi.exceptions import HTTPException

from src.database.repositories import TicketRepository


async def ticket_exists(id: UUID) -> TicketRepository:
    ticket_repository = TicketRepository()

    ticket = await ticket_repository.get(id)
    if not ticket:
        raise HTTPException(status_code=404, detail="Ticket not found")

    return ticket_repository
