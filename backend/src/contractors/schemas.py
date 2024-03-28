import datetime
import json
from uuid import UUID

from pydantic import BaseModel, model_validator, EmailStr


class CreateContractorRequest(BaseModel):
    name: str
    email: EmailStr

    @model_validator(mode="before")
    @classmethod
    def validate_to_json(cls, value):
        if isinstance(value, str):
            return cls(**json.loads(value))
        return value


class ContractorResponse(BaseModel):
    id: UUID
    name: str

    class Config:
        from_attributes = True
