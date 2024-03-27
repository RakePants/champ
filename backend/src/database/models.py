import uuid

from sqlalchemy import Column, Float, Integer, String
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import DeclarativeBase, Mapped


class Base(DeclarativeBase):
    pass


class Ticket(Base):
    __tablename__ = "tickets"

    id: Mapped[uuid.UUID] = Column(
        UUID(as_uuid=True), primary_key=True, default=uuid.uuid4
    )
    latitude: Mapped[float] = Column(Float)
    longitude: Mapped[float] = Column(Float)
    address: Mapped[str] = Column(String(255))
    type: Mapped[str] = Column(String(255))
    volume: Mapped[int] = Column(Integer)
