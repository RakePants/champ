from pydantic import BaseModel
from pathlib import Path

HOME = Path(__file__).parent.parent.parent / "vision_models"


class DinoConfig(BaseModel):
    GROUNDING_DINO_CONFIG_PATH: str = HOME / "GroundingDINO/groundingdino/config/GroundingDINO_SwinT_OGC.py"
    GROUNDING_DINO_CHECKPOINT_PATH: str = HOME / "weights" / "groundingdino_swint_ogc.pth"


class SamConfig(BaseModel):
    SAM_ENCODER_VERSION: str = "vit_h"
    SAM_CHECKPOINT_PATH: str = HOME / "weights" / "sam_vit_h_4b8939.pth"


sam_config = SamConfig()
dino_config = DinoConfig()
