from abc import ABC, abstractmethod
from typing import List
from uuid import UUID

from sqlalchemy import pool, select
from sqlalchemy.ext.asyncio import AsyncSession, async_sessionmaker, create_async_engine

from src.config import settings
from src.database import models

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
