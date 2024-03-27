import asyncio
import logging
from typing import List
from uuid import UUID

from fastapi import APIRouter, Body, Depends, File, UploadFile, status
from fastapi.encoders import jsonable_encoder
from fastapi.exceptions import HTTPException

from src.database.repositories import TicketRepository

tickets_router = APIRouter(tags=["Tickets"])

"""
@tickets_router.post(
    "/create_ticket", status_code=status.HTTP_201_CREATED, response_model=TicketResponse
)
async def create_ticket(
    create_ticket_request: CreateTicketRequest = Body(...),
):
    ticket = await add_ticket(create_ticket_request, TicketRepository())

    # Return the created vault representation
    return TicketResponse.model_validate(ticket)
"""

"""
@vaults_router.delete("/delete_vault", status_code=status.HTTP_204_NO_CONTENT)
async def delete_vault(
    vault_id: UUID = Body(...),
    vault_repository: VaultRepository = Depends(vault_exists),
):  
    vault_type = await vault_repository.get_vault_type(vault_id)
    await vault_repository.delete(vault_id)
    if vault_type == VaultType.GRAPH:
        await send_delete_request_to_graph_kb_service(body=jsonable_encoder(vault_id))
    else:
        await send_delete_request_to_vector_kb_service(body=jsonable_encoder(vault_id))


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