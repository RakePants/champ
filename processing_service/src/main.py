from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from src.processing.router import processing_router

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
def root():
    return {"message": "Hello world!"}


app.include_router(processing_router, prefix="/processing")
