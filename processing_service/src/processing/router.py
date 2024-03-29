from fastapi import APIRouter, File, UploadFile, status, Response

from src.processing.utils import process_image, process_speech_to_text

processing_router = APIRouter(tags=["Processing"])


@processing_router.post("/image", status_code=status.HTTP_200_OK)
def image(
    image: UploadFile = File(...),
) -> Response:
    orig_image_bytes = image.file.read()
    masked_image_bytes = process_image(orig_image_bytes)
    return Response(content=masked_image_bytes, media_type="image/jpg")


@processing_router.post("/speech_to_text", status_code=status.HTTP_200_OK)
def speech_to_text(
    audio_file: UploadFile = File(...),
):
    audio = process_speech_to_text(audio_file)
    return audio
