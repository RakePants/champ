import cv2
import numpy as np
import asyncio
from groundingdino.util.inference import Model
from src.models.config import dino_config, sam_config
from functools import partial
from segment_anything import sam_model_registry, SamPredictor
import supervision as sv


sam = sam_model_registry[sam_config.SAM_ENCODER_VERSION](checkpoint=sam_config.SAM_CHECKPOINT_PATH).to(device="cuda")
sam_predictor = SamPredictor(sam)

grounding_dino_model = Model(model_config_path=dino_config.GROUNDING_DINO_CONFIG_PATH,
                             model_checkpoint_path=dino_config.GROUNDING_DINO_CHECKPOINT_PATH)
CLASSES = ['pothole', 'crack', 'roadwork']
BOX_TRESHOLD = 0.35
TEXT_TRESHOLD = 0.25


def enhance_class_name(class_names: list[str]) -> list[str]:
    return [
        f"all {class_name}s"
        for class_name
        in class_names
    ]


def segment(sam_predictor: SamPredictor, image: np.ndarray, xyxy: np.ndarray) -> np.ndarray:
    sam_predictor.set_image(image)
    result_masks = []
    for box in xyxy:
        masks, scores, logits = sam_predictor.predict(
            box=box,
            multimask_output=True
        )
        index = np.argmax(scores)
        result_masks.append(masks[index])
    return np.array(result_masks)


def predict_masks(image: np.ndarray) -> np.ndarray:
    detections = grounding_dino_model.predict_with_classes(
        image=image,
        classes=enhance_class_name(class_names=CLASSES),
        box_threshold=BOX_TRESHOLD,
        text_threshold=TEXT_TRESHOLD)

    detections.mask = segment(
        sam_predictor=sam_predictor,
        image=cv2.cvtColor(image, cv2.COLOR_BGR2RGB),
        xyxy=detections.xyxy
    )

    box_annotator = sv.BoxAnnotator()
    mask_annotator = sv.MaskAnnotator()
    labels = [
        f"{CLASSES[class_id]} {confidence:0.2f}"
        for _, _, confidence, class_id, _
        in detections]
    annotated_image = mask_annotator.annotate(scene=image.copy(), detections=detections)
    annotated_image = box_annotator.annotate(scene=annotated_image, detections=detections, labels=labels)

    return annotated_image
