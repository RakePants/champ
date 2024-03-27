import datetime
import uuid

from sqlalchemy import Column, DateTime, Float, ForeignKey, Integer, String, Text
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column, relationship


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
    description: Mapped[str] = Column(String(255))
    timestamp: Mapped[DateTime] = Column(DateTime, default=datetime.datetime.now)
    type: Mapped[str] = Column(String(255))
    volume: Mapped[int] = Column(Integer)
    image: Mapped[str] = Column(Text)  # Base64 image data
