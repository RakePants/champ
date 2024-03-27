import asyncio
import logging
from typing import List
from uuid import UUID

from fastapi import APIRouter, File, UploadFile, status

processing_router = APIRouter(tags=["Processing"])


@processing_router.post("/image", status_code=status.HTTP_200_OK)
async def process_image(
    image: UploadFile = File(...),
):
    image = image.file
    return image


@processing_router.post("/speech_to_text", status_code=status.HTTP_200_OK)
async def speech_to_text(
    audio: UploadFile = File(...),
):
    audio = audio.file

    return audio
