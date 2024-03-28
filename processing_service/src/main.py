from fastapi import FastAPI

from src.processing.router import processing_router

app = FastAPI()


@app.get("/")
def root():
    return {"message": "Hello world!"}


app.include_router(processing_router, prefix="/processing")
