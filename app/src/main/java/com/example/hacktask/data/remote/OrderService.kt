package com.example.hacktask.data.remote

import com.example.hacktask.domain.repository.IOrderRepository
import com.example.hacktask.model.Order
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.io.File

data class TicketFormData(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("address") val address: String,
    @SerializedName("description") val description: String,
    @SerializedName("type") val type: String,
    @SerializedName("volume") val volume: Int
)

interface OrderService {
    @Multipart
    @POST("your_endpoint_here")
    fun createTicket(
        @Part("create_ticket_request") ticketData: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<Void>

    @GET("orders/{id}")
    fun getOrderById(@Path("id") orderId: String): Order

    @GET("orders")
    fun getAllOrders(): List<Order>

}

class OrderRepositryImpl : IOrderRepository {

    private val orderService: OrderService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("your_base_url_here")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        orderService = retrofit.create(OrderService::class.java)
    }

    override suspend fun createOrder(
        latitude: Double,
        longitude: Double,
        address: String,
        description: String,
        type: String,
        volume: Int,
        imageFile: File
    ) {
        val formData = TicketFormData(latitude, longitude, address, description, type, volume)

        val ticketData = RequestBody.create("application/json".toMediaType(), Gson().toJson(formData))
        val imageBody = RequestBody.create("image/*".toMediaType(), imageFile)
        val imagePart = MultipartBody.Part.createFormData("images", imageFile.name, imageBody)

        orderService.createTicket(ticketData, imagePart).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Обработка успешного ответа
                } else {
                    // Обработка ошибки
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Обработка ошибки
            }
        })
    }

    override suspend fun getAllOrders(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrderById(id: String): Order {
        TODO("Not yet implemented")
    }
}