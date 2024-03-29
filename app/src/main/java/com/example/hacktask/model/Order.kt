package com.example.hacktask.model

data class Order(
    val id: Int,
    val latitude: Float,
    val longitude: Float,
    val address: String,
    val description: String,
    val timestamp: String,
    val type: String,
    val volume: Int,
    val status: String,
    val image: String,
    val completionImage: String?,
    val completionTimestamp: String?,
)

//id: Mapped[uuid.UUID] = Column(
//UUID(as_uuid=True), primary_key=True, default=uuid.uuid4
//)
//latitude: Mapped[float] = Column(Float)
//longitude: Mapped[float] = Column(Float)
//address: Mapped[str] = Column(String(255))
//description: Mapped[str] = Column(String(255))
//timestamp: Mapped[DateTime] = Column(DateTime, default=datetime.datetime.now)
//type: Mapped[str] = Column(String(255))
//volume: Mapped[int] = Column(Integer)
//status: Mapped[str] = Column(String(64))
//image: Mapped[str] = Column(Text)  # Base64 image data
//completion_image: Mapped[str | None] = Column(
//Text, nullable=True
//)  # Base64 image data
//completion_timestamp: Mapped[DateTime | None] = Column(
//DateTime, nullable=True
//)