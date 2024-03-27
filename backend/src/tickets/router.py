import asyncio
import logging
from typing import List
from uuid import UUID

from fastapi import APIRouter, Body, Depends, File, UploadFile, status
from fastapi.encoders import jsonable_encoder
from fastapi.exceptions import HTTPException

from src.database.repositories import TicketRepository
from src.tickets.schemas import (
    CreateTicketRequest,
    TicketResponse,
    TicketCreationResponse,
)
from src.tickets.utils import add_ticket
from src.tickets.dependencies import ticket_exists

tickets_router = APIRouter(tags=["Tickets"])


@tickets_router.post(
    "/create_ticket",
    status_code=status.HTTP_201_CREATED,
    response_model=TicketCreationResponse,
)
async def create_ticket(
    create_ticket_request: CreateTicketRequest = Body(...),
    image: UploadFile = File(...),
):
    ticket = await add_ticket(create_ticket_request, image, TicketRepository())

    return TicketCreationResponse.model_validate(ticket)


@tickets_router.delete("/delete_ticket", status_code=status.HTTP_204_NO_CONTENT)
async def delete_ticket(
    ticket_id: UUID = Body(...),
    ticket_repository: TicketRepository = Depends(ticket_exists),
):
    await ticket_repository.delete(ticket_id)


@tickets_router.get(
    "/get_tickets",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_tickets():
    ticket_repository = TicketRepository()

    tickets = await ticket_repository.get_all()
    print(type(tickets[0]))
    return [TicketResponse.model_validate(ticket) for ticket in tickets]


"""
@vaults_router.post(
    "/get_vault_documents",
    status_code=status.HTTP_200_OK,
    response_model=List[DocumentResponse],
)
async def get_vault_documents(
    vault_id: UUID = Body(...),
    vault_repository: VaultRepository = Depends(vault_exists),
):
    documents = await vault_repository.get_vault_documents(vault_id)

    return [DocumentResponse.model_validate(document) for document in documents]


@vaults_router.post(
    "/get_users_vaults",
    status_code=status.HTTP_200_OK,
    response_model=List[VaultResponse],
)
async def get_users_vaults(user_id: UUID = Body(...)):
    vault_repository = VaultRepository()

    vaults = await vault_repository.get_users_vaults(user_id)

    return [VaultResponse.model_validate(vault) for vault in vaults]
"""
