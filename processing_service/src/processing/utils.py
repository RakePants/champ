from fastapi import File

from src.models.whisper import pipe


def process_image(image_file: File) -> File:
    return image_file


def process_speech_to_text(audio_file: File) -> str:
    with open(audio_file, "rb") as opened_audio_file:
        # Read the entire file's contents into a bytes object
        audio_bytes = opened_audio_file.read()

    result = pipe(audio_bytes)
    return result["text"]
