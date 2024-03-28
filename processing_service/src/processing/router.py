from fastapi import APIRouter, File, UploadFile, status

from src.processing.utils import process_image, process_speech_to_text

processing_router = APIRouter(tags=["Processing"])


@processing_router.post("/image", status_code=status.HTTP_200_OK)
def image(
    image: UploadFile = File(...),
):
    image = image.file
    image = process_image(image)
    return image


@processing_router.post("/speech_to_text", status_code=status.HTTP_200_OK)
def speech_to_text(
    audio: UploadFile = File(...),
):
    audio = audio.file
    audio = process_speech_to_text(audio)
    return audio
