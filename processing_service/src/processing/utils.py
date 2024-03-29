from fastapi import File

from src.models.whisper import pipe


def process_image(image_file: File) -> File:
    return image_file


def process_speech_to_text(audio_file: File) -> str:
    result = pipe(audio_file)
    return result["text"]
