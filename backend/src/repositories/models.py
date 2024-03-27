from sqlalchemy import UUID
from sqlalchemy.orm import DeclarativeBase, mapped_column


class Base(DeclarativeBase):
    pass


class Document(Base):
    __tablename__ = "tickets"

    id = mapped_column(UUID(as_uuid=True), primary_key=True)
