FROM python:3.11.7-slim

RUN addgroup --gid 10001 champ_user && \
    adduser --uid 10001 --gid 10001 --disabled-password --gecos "" champ_user

WORKDIR /usr/src/processing_service

ENV PYTHONPATH=/usr/src/processing_service/src

COPY /requirements/base.txt /requirements/

RUN apt-get update && \
    pip install --upgrade pip && \
    pip install -U pip setuptools wheel && \
    pip install -r /requirements/base.txt && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --chown=champ_user:champ_user . .

USER champ_user