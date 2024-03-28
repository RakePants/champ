import asyncio
import logging
from typing import List
from uuid import UUID

from fastapi import APIRouter, Body, Depends, File, UploadFile, status
from fastapi.encoders import jsonable_encoder
from fastapi.exceptions import HTTPException

from src.contractors.dependencies import contractor_exists
from src.contractors.schemas import ContractorResponse, CreateContractorRequest
from src.contractors.utils import add_contractor
from src.database.repositories import ContractorRepository
from src.tickets.schemas import TicketResponse

contractors_router = APIRouter(tags=["Contractors"])


@contractors_router.post(
    "/create",
    status_code=status.HTTP_201_CREATED,
    response_model=ContractorResponse,
)
async def create(
    create_contractor_request: CreateContractorRequest = Body(...),
):
    contractor = await add_contractor(create_contractor_request, ContractorRepository())

    return ContractorResponse.model_validate(contractor)


@contractors_router.delete("/delete", status_code=status.HTTP_204_NO_CONTENT)
async def delete(
    id: UUID,
    contractor_repository: ContractorRepository = Depends(contractor_exists),
):
    await contractor_repository.delete(id)


@contractors_router.get(
    "/get_by_id",
    status_code=status.HTTP_200_OK,
    response_model=ContractorResponse,
)
async def get_by_id(
    id: UUID,
    contractor_repository: ContractorRepository = Depends(contractor_exists),
):
    contractor = await contractor_repository.get(id)

    return ContractorResponse.model_validate(contractor)


@contractors_router.get(
    "/get_all",
    status_code=status.HTTP_200_OK,
    response_model=List[ContractorResponse],
)
async def get_all():
    contractor_repository = ContractorRepository()
    contractors = await contractor_repository.get_all()

    return [ContractorResponse.model_validate(contractor) for contractor in contractors]


@contractors_router.get(
    "/get_due_contractor_tickets",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_due_contractor_tickets(
    id: UUID,
    contractor_repository: ContractorRepository = Depends(contractor_exists),
):
    tickets = await contractor_repository.get_due_contractor_tickets(id)

    return [TicketResponse.model_validate(ticket) for ticket in tickets]


@contractors_router.get(
    "/get_completed_contractor_tickets",
    status_code=status.HTTP_200_OK,
    response_model=List[TicketResponse],
)
async def get_completed_contractor_tickets(
    id: UUID,
    contractor_repository: ContractorRepository = Depends(contractor_exists),
):
    tickets = await contractor_repository.get_completed_contractor_tickets(id)

    return [TicketResponse.model_validate(ticket) for ticket in tickets]
