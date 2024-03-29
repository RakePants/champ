FROM pytorch/pytorch:2.2.2-cuda11.8-cudnn8-runtime

RUN addgroup --gid 10001 champ_user && \
    adduser --uid 10001 --gid 10001 --disabled-password --gecos "" champ_user

WORKDIR /usr/src/processing_service

ENV PYTHONPATH=/usr/src/processing_service \
    ROOT=/usr/src/processing_service \
    MODELS_DIR=/usr/src/processing_service/vision_models \
    CUDA_HOME=/usr/local/cuda-11.8/

WORKDIR $ROOT

COPY /requirements/base.txt /requirements/

RUN apt-get update && \
    apt-get install ffmpeg libsm6 libxext6 git -y && \
    pip install --upgrade pip && \
    pip install -U pip setuptools wheel && \
    pip install -U torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu118 && \
    pip install -r /requirements/base.txt && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --chown=champ_user:champ_user . .

RUN cd $MODELS_DIR/GroundingDINO && python -m pip install --no-cache-dir --no-build-isolation -e .

RUN pip uninstall -y supervision && \
    pip install supervision==0.6.0 && \
    pip install opencv-python Cython

RUN pip install 'git+https://github.com/facebookresearch/segment-anything.git'

USER champ_user
