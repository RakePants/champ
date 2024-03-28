import datetime
import json
from enum import Enum
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


class AcceptTicketRequest(BaseModel):
    contractor_id: UUID
    completion_target_date: datetime.date


class TicketResponse(BaseModel):
    id: UUID
    latitude: float
    longitude: float
    address: str
    description: str
    timestamp: datetime.datetime
    type: str
    volume: int
    status: TicketStatus
    original_image: str
    processed_image: str
    completion_image: str | None
    completion_timestamp: datetime.datetime | None
    contractor_id: UUID | None
    completion_target_date: datetime.date | None


    class Config:
        from_attributes = True


class TicketCreationResponse(BaseModel):
    id: UUID
    timestamp: datetime.datetime

    class Config:
        from_attributes = True
