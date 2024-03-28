import asyncio
import logging
from typing import List
from uuid import UUID

from fastapi import APIRouter, Body, Depends, File, UploadFile, status
from fastapi.encoders import jsonable_encoder
from fastapi.exceptions import HTTPException

from src.database.repositories import TicketRepository
from src.tickets.dependencies import ticket_exists
from src.tickets.schemas import (
    CreateTicketRequest,
    AcceptTicketRequest,
    TicketCreationResponse,
    TicketResponse,
    TicketStatus,
)
from src.tickets.utils import add_ticket, complete_ticket, accept_ticket

tickets_router = APIRouter(tags=["Tickets"])


@tickets_router.post(
    "/create",
    status_code=status.HTTP_201_CREATED,
    response_model=TicketCreationResponse,
)
async def create(
    create_ticket_request: CreateTicketRequest = Body(...),
    image: UploadFile = File(...),
):
    ticket = await add_ticket(create_ticket_request, image, TicketRepository())

    return TicketCreationResponse.model_validate(ticket)


@tickets_router.delete("/delete", status_code=status.HTTP_204_NO_CONTENT)
async def delete(
    id: UUID,
    ticket_repository: TicketRepository = Depends(ticket_exists),
):
    await ticket_repository.delete(id)


@tickets_router.get(
    "/get_by_id",
    status_code=status.HTTP_200_OK,
    response_model=TicketResponse,
)
async def get_by_id(
    id: UUID,
    ticket_repository: TicketRepository = Depends(ticket_exists),
):
    ticket = await ticket_repository.get(id)

    return TicketResponse.model_validate(ticket)


@tickets_router.get(
    "/get_all",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_all():
    ticket_repository = TicketRepository()
    tickets = await ticket_repository.get_all()

    return [TicketResponse.model_validate(ticket) for ticket in tickets]


@tickets_router.get(
    "/get_new",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_new_tickets():
    ticket_repository = TicketRepository()
    tickets = await ticket_repository.get_new()

    return [TicketResponse.model_validate(ticket) for ticket in tickets]


@tickets_router.get(
    "/get_accepted",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_accepted_tickets():
    ticket_repository = TicketRepository()
    tickets = await ticket_repository.get_accepted()

    return [TicketResponse.model_validate(ticket) for ticket in tickets]


@tickets_router.get(
    "/get_declined",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_declined():
    ticket_repository = TicketRepository()
    tickets = await ticket_repository.get_declined()

    return [TicketResponse.model_validate(ticket) for ticket in tickets]


@tickets_router.get(
    "/get_completed",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_completed():
    ticket_repository = TicketRepository()
    tickets = await ticket_repository.get_completed()

    return [TicketResponse.model_validate(ticket) for ticket in tickets]


@tickets_router.put(
    "/accept",
    status_code=status.HTTP_200_OK,
    response_model=TicketResponse,
)
async def accept(
    id: UUID,
    accept_ticket_request: AcceptTicketRequest,
    ticket_repository: TicketRepository = Depends(ticket_exists),
):
    ticket = await accept_ticket(id, accept_ticket_request, ticket_repository)

    return TicketResponse.model_validate(ticket)


@tickets_router.put(
    "/decline",
    status_code=status.HTTP_200_OK,
    response_model=TicketResponse,
)
async def decline(
    id: UUID,
    ticket_repository: TicketRepository = Depends(ticket_exists),
):
    ticket = await ticket_repository.decline(id)

    return TicketResponse.model_validate(ticket)


@tickets_router.put(
    "/complete",
    status_code=status.HTTP_200_OK,
    response_model=TicketResponse,
)
async def complete(
    id: UUID,
    completion_image: UploadFile = File(...),
    ticket_repository: TicketRepository = Depends(ticket_exists),
):
    ticket = await complete_ticket(id, completion_image, ticket_repository)

    return TicketResponse.model_validate(ticket)
