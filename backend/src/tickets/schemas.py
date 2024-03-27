import json
from datetime import datetime
from enum import Enum
from typing import List
from uuid import UUID

from pydantic import BaseModel, model_validator


class TicketStatus(str, Enum):
    NEW = "new"
    DECLINED = "declined"
    ACCEPTED = "accepted"
    COMPLETED = "completed"

class CreateTicketRequest(BaseModel):
    latitude: float
    longitude: float
    address: str
    description: str
    type: str
    volume: int

    @model_validator(mode="before")
    @classmethod
    def validate_to_json(cls, value):
        if isinstance(value, str):
            return cls(**json.loads(value))
        return value


class Document(BaseModel):
    document_id: UUID
    text: str


class RequestToGraphKBService(BaseModel):
    vault_id: UUID
    documents: List[Document]


class TicketResponse(BaseModel):
    id: UUID
    timestamp: datetime

    class Config:
        from_attributes = True
