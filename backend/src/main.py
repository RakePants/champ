from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from src.contractors.router import contractors_router
from src.tickets.router import tickets_router

app = FastAPI()


# Add CORS middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/")
async def root():
    return {"message": "Hello world!"}


app.include_router(tickets_router, prefix="/tickets")
app.include_router(contractors_router, prefix="/contractors")
