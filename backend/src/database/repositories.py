import datetime
from abc import ABC, abstractmethod
from typing import List
from uuid import UUID

from fastapi.exceptions import HTTPException
from sqlalchemy import pool, select
from sqlalchemy.exc import IntegrityError
from sqlalchemy.ext.asyncio import AsyncSession, async_sessionmaker, create_async_engine

from src.config import settings
from src.database import models
from src.tickets.schemas import TicketStatus

engine = create_async_engine(
    settings.database_url,
    poolclass=pool.AsyncAdaptedQueuePool,
    pool_size=12,
    max_overflow=4,
    pool_pre_ping=True,
)

Session = async_sessionmaker(bind=engine, class_=AsyncSession, expire_on_commit=False)


class AbstractRepository(ABC):
    @abstractmethod
    async def add(self, entity):
        raise NotImplementedError

    @abstractmethod
    async def get(self, entity_id):
        raise NotImplementedError


class TicketRepository(AbstractRepository):
    def __init__(self):
        self.session = Session()

    async def add(self, entity):
        async with self.session as session:
            async with session.begin():
                session.add(entity)

    async def get(self, id: UUID) -> models.Ticket | None:
        async with self.session as session:
            document = await session.get(models.Ticket, id)
            return document

    async def get_all(self) -> List[models.Ticket] | None:
        async with self.session as session:
            query = select(models.Ticket)
            result = await session.execute(query)
            return result.scalars().all()

    async def get_new(self) -> List[models.Ticket] | None:
        async with self.session as session:
            query = select(models.Ticket).where(models.Ticket.status == "new")
            result = await session.execute(query)
            return result.scalars().all()

    async def get_accepted(self) -> List[models.Ticket] | None:
        async with self.session as session:
            query = select(models.Ticket).where(models.Ticket.status == "accepted")
            result = await session.execute(query)
            return result.scalars().all()

    async def get_declined(self) -> List[models.Ticket] | None:
        async with self.session as session:
            query = select(models.Ticket).where(models.Ticket.status == "declined")
            result = await session.execute(query)
            return result.scalars().all()

    async def get_completed(self) -> List[models.Ticket] | None:
        async with self.session as session:
            query = select(models.Ticket).where(models.Ticket.status == "completed")
            result = await session.execute(query)
            return result.scalars().all()

    async def delete(self, id: UUID):
        async with self.session as session:
            async with session.begin():
                ticket = await session.get(models.Ticket, id)
                await session.delete(ticket)

    async def decline(self, id: UUID):
        async with self.session as session:
            async with session.begin():
                ticket = await session.get(models.Ticket, id)
                ticket.status = TicketStatus.DECLINED
                ticket.contractor_id = None
                ticket.completion_target_date = None
                ticket.completion_image = None
                ticket.completion_timestamp = None
                return ticket

    async def make_new(self, id: UUID):
        async with self.session as session:
            async with session.begin():
                ticket = await session.get(models.Ticket, id)
                ticket.status = TicketStatus.NEW
                ticket.contractor_id = None
                ticket.completion_target_date = None
                ticket.completion_image = None
                ticket.completion_timestamp = None
                return ticket

    async def complete(self, id: UUID, image_bytes: str, timestamp: datetime.datetime):
        async with self.session as session:
            async with session.begin():
                ticket = await session.get(models.Ticket, id)
                ticket.status = TicketStatus.COMPLETED
                ticket.completion_image = image_bytes
                ticket.completion_timestamp = timestamp
                return ticket

    async def accept(
        self, id: UUID, contractor_id: str, completion_target_date: datetime.date
    ):
        async with self.session as session:
            async with session.begin():
                ticket = await session.get(models.Ticket, id)
                ticket.status = TicketStatus.ACCEPTED
                ticket.contractor_id = contractor_id
                ticket.completion_target_date = completion_target_date
                return ticket


class ContractorRepository(AbstractRepository):
    def __init__(self):
        self.session = Session()

    async def add(self, entity):
        async with self.session as session:
            try:
                async with session.begin():
                    session.add(entity)
            except IntegrityError:
                raise HTTPException(
                    status_code=400, detail="Contractor already exists"
                )

    async def get(self, id: UUID) -> models.Contractor | None:
        async with self.session as session:
            vault = await session.get(models.Contractor, id)
            return vault

    async def get_all(self) -> List[models.Contractor] | None:
        async with self.session as session:
            query = select(models.Contractor)
            result = await session.execute(query)
            return result.scalars().all()

    async def get_due_contractor_tickets(self, id: UUID) -> List[models.Ticket] | None:
        async with self.session as session:
            tickets = await session.execute(
                select(models.Ticket).where(
                    models.Ticket.contractor_id == id,
                    models.Ticket.status == TicketStatus.ACCEPTED,
                )
            )
            return tickets.scalars().all()

    async def get_completed_contractor_tickets(
        self, id: UUID
    ) -> List[models.Ticket] | None:
        async with self.session as session:
            tickets = await session.execute(
                select(models.Ticket).where(
                    models.Ticket.contractor_id == id,
                    models.Ticket.status == TicketStatus.COMPLETED,
                )
            )
            return tickets.scalars().all()

    async def delete(self, id: UUID) -> None:
        async with self.session as session:
            async with session.begin():
                entity = await session.get(models.Contractor, id)

                if entity:
                    # Query and change all tickets associated with the contractor
                    tickets = await session.execute(
                        select(models.Ticket).where(models.Ticket.contractor_id == id)
                    )
                    for ticket in tickets.scalars().all():
                        ticket.status = TicketStatus.NEW
                        ticket.contractor_id = None
                        ticket.completion_target_date = None

                    await session.delete(entity)
