"""Ticket revision

Revision ID: 9321e31bd573
Revises: 598165d7f64b
Create Date: 2024-03-27 17:15:04.507249

"""
from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision: str = '9321e31bd573'
down_revision: Union[str, None] = '598165d7f64b'
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    pass


def downgrade() -> None:
    pass
