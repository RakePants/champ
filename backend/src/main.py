from fastapi import FastAPI

from src.contractors.router import contractors_router
from src.tickets.router import tickets_router

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello world!"}


app.include_router(tickets_router, prefix="/tickets")
app.include_router(contractors_router, prefix="/contractors")
