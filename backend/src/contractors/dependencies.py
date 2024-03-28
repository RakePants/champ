from uuid import UUID

from fastapi import Body
from fastapi.exceptions import HTTPException

from src.database.repositories import ContractorRepository


async def contractor_exists(id: UUID) -> ContractorRepository:
    contractor_repository = ContractorRepository()

    contractor = await contractor_repository.get(id)
    if not contractor:
        raise HTTPException(status_code=404, detail="Contractor not found")

    return contractor_repository
