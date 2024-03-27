from fastapi import FastAPI

from src.tickets.router import tickets_router

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello world!"}


app.include_router(tickets_router)
