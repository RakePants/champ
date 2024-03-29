package com.example.hacktask.features.add_order.presentation


import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacktask.data.remote.OrderService
import com.example.hacktask.data.repository.OrderRepositoryImpl
import com.example.hacktask.domain.usecases.CreateOrderUsecase
import com.example.hacktask.model.Order
import com.example.hacktask.utils.AppDispatchers
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddOrderViewModel: ContainerHost<AddOrderState, AddOrderSideEffect>, ViewModel() {
    override val container = container<AddOrderState, AddOrderSideEffect>(AddOrderState())

    val address = mutableStateOf("")
    val lattitude = mutableStateOf(0.0)
    val longitude = mutableStateOf(0.0)
    val volume = mutableStateOf("")
    val description = mutableStateOf("")
    val type = mutableStateOf("Выберите тип")
    val picture = mutableStateOf<Bitmap?>(null)

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://2.58.70.16:8000/docs#/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var orderService = retrofit.create(OrderService::class.java)

    init {
        orderService = retrofit.create(OrderService::class.java)
    }

    fun getCoords(context: Context) = intent {
        var fusedLocationClient: FusedLocationProviderClient? = null

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context as ComponentActivity)

        // Проверяем, есть ли у нас разрешение на доступ к местоположению

        // Проверяем, есть ли у нас разрешение на доступ к местоположению
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Если разрешение есть, запрашиваем местоположение
            fusedLocationClient!!.lastLocation
                .addOnSuccessListener(
                    context as ComponentActivity
                ) { location ->
                    // Получаем местоположение
                    if (location != null) {
                        val localLatitude = location.latitude
                        val localLongitude = location.longitude
                        lattitude.value = localLatitude
                        longitude.value = localLongitude
                        viewModelScope.launch(AppDispatchers.ui) {
                            reduce {
                                state.copy(
                                    lattitude = localLatitude,
                                    longitude = localLongitude,
                                )
                            }
                        }
                    }
                }
        } else {
            // Если разрешения нет, запрашиваем разрешение у пользователя
            ActivityCompat.requestPermissions(
                context as ComponentActivity,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
        Log.d("Test", "getCoords: ${state.lattitude} ${state.longitude}")
    }

    fun typingLatitude(text: String) = intent {
        reduce {
            state.copy(lattitude = text.toDouble())
        }
    }

    fun typingLongitude(text: String) = intent {
        reduce {
            state.copy(longitude = text.toDouble())
        }
    }

    fun typingAddress(text: String) = intent {
        reduce {
            state.copy(address = text)
        }
        Log.d("test", "typingDescription: ${state.address}")
    }

    fun typingDescription(text: String) = intent {
        reduce {
            state.copy(description = text)
        }
        Log.d("test", "typingDescription: ${state.description}")
    }

    fun chooseType(text: String) = intent {
        reduce {
            state.copy(type = text)
        }
        Log.d("test", "chooseType: ${state.type}")
    }

    fun typingVolume(text: String) = intent {
        reduce {
            state.copy(volume = text)
        }
    }

    fun takePhoto() = intent {
        reduce {
            state.copy(photo = null)
        }
    }

    fun sendResponseOrder() {
        viewModelScope.launch(
            AppDispatchers.io
        ) {
            val usecase = CreateOrderUsecase(repository = OrderRepositoryImpl(orderService = orderService))
            usecase.execute(
                Order(

                )
            )
        }
    }

}