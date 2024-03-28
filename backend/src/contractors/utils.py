import base64
import uuid
import datetime
from fastapi import UploadFile

from src.database.models import Contractor
from src.database.repositories import ContractorRepository
from src.contractors.schemas import CreateContractorRequest


async def add_contractor(
    create_contractor_request: CreateContractorRequest,
    contractor_repository: ContractorRepository,
) -> Contractor:
    id = uuid.uuid4()  # Generate random unique identifier

    contractor = Contractor(
        id=id,
        name=create_contractor_request.name,
        email=create_contractor_request.email,
    )

    await contractor_repository.add(contractor)

    return contractor
