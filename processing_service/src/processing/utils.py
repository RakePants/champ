import numpy as np
from fastapi import File
from src.models.preprocess_img import predict_masks
from src.models.whisper import pipe
import cv2


def process_image(image_bytes: bytes):
    nparr = np.fromstring(image_bytes, np.uint8)
    img_np = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    masked_arr_img = predict_masks(img_np)
    success, masked_byte_img = cv2.imencode(".jpg", masked_arr_img)
    return masked_byte_img.tobytes()


def process_speech_to_text(audio_file: File) -> str:
    audio_bytes = audio_file.file.read()

    result = pipe(audio_bytes)
    return result["text"]
