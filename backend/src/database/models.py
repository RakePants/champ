import datetime
import uuid

from sqlalchemy import Column, DateTime, Date, Float, ForeignKey, Integer, String, Text
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column, relationship


class Base(DeclarativeBase):
    pass


class Contractor(Base):
    __tablename__ = "contractors"

    id: Mapped[uuid.UUID] = Column(
        UUID(as_uuid=True), primary_key=True, default=uuid.uuid4
    )
    name: Mapped[str] = Column(String(255))
    email: Mapped[str] = Column(String(255), unique=True)


class Ticket(Base):
    __tablename__ = "tickets"

    id: Mapped[uuid.UUID] = Column(
        UUID(as_uuid=True), primary_key=True, default=uuid.uuid4
    )
    latitude: Mapped[float] = Column(Float)
    longitude: Mapped[float] = Column(Float)
    address: Mapped[str] = Column(String(255))
    description: Mapped[str] = Column(String(255))
    timestamp: Mapped[DateTime] = Column(DateTime, default=datetime.datetime.now)
    type: Mapped[str] = Column(String(255))
    volume: Mapped[int] = Column(Integer)
    status: Mapped[str] = Column(String(64))
    image: Mapped[str] = Column(Text)  # Base64 image data

    contractor_id: Mapped[uuid.UUID | None] = Column(
        UUID(as_uuid=True), ForeignKey("contractors.id"), nullable=True
    )
    completion_target_date: Mapped[Date | None] = Column(Date, nullable=True)
    completion_image: Mapped[str | None] = Column(
        Text, nullable=True
    )  # Base64 image data
    completion_timestamp: Mapped[DateTime | None] = Column(DateTime, nullable=True)
