import json
from datetime import datetime
from enum import Enum
from typing import List
from uuid import UUID

from pydantic import BaseModel, model_validator


class VaultType(str, Enum):
    GRAPH = "graph"
    VECTOR = "vector"


class CreateVaultRequest(BaseModel):
    user_id: UUID
    vault_name: str
    vault_type: VaultType

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


class VaultResponse(BaseModel):
    id: UUID
    name: str
    type: VaultType
    created_at: datetime
    user_id: UUID

    class Config:
        from_attributes = True


class DocumentResponse(BaseModel):
    id: UUID
    name: str
    text: str
    vault_id: UUID

    class Config:
        from_attributes = True
