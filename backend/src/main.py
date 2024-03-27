from fastapi import FastAPI

from src.tickets.router import tickets_router
from src.processing.router import processing_router

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello world!"}


app.include_router(tickets_router, prefix="/tickets")
app.include_router(processing_router, prefix="/processing")
