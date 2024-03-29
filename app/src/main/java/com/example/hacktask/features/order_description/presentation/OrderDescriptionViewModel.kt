package com.example.hacktask.features.order_description.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacktask.domain.usecases.GetOrderUsecase
import com.example.hacktask.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class OrderDescriptionViewModel @Inject constructor(

): ContainerHost<OrderDescriptionState, OrderDescriptionSideEffect>, ViewModel() {
    override val container = container<OrderDescriptionState, OrderDescriptionSideEffect>(OrderDescriptionState())

    val screenState = mutableStateOf<OrderDescriptionScreenState>(OrderDescriptionScreenState.Loading)

    init {
        loadData()
    }

    fun getIdForResponse(id: Int) = intent{
        reduce {
            state.copy(id = id)
        }
    }

    private fun loadData() = intent {
        postSideEffect(OrderDescriptionSideEffect.ShowLoading)
        val getOrderUsecase = GetOrderUsecase()
        viewModelScope.launch(AppDispatchers.io) {
            val order = getOrderUsecase.execute(state.id)
            reduce {
                state.copy(
                    id = order.id,
                    latitude = order.latitude,
                    longitude = order.longitude,
                    address = order.address,
                    description = order.description,
                    timestamp = order.timestamp,
                    type = order.type,
                    volume = order.volume,
                    status = order.status,
                    image = order.image,
                    completionImage = order.completionImage,
                    completionTimestamp = order.completionTimestamp
                )
            }
            postSideEffect(OrderDescriptionSideEffect.ShowSuccess)
        }
    }
}