package com.example.hacktask.features.order.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacktask.domain.usecases.GetAllOrdersUsecase
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
class OrderViewModel @Inject constructor(

): ContainerHost<OrderState, OrderSideEffect>, ViewModel() {
    override val container = container<OrderState, OrderSideEffect>(OrderState())

    val screenState = mutableStateOf<OrderScreenState>(OrderScreenState.Loading)

    init {
        loadData()
    }

    private fun loadData() = intent{
        postSideEffect(OrderSideEffect.ShowLoading)
        viewModelScope.launch(AppDispatchers.io) {
            val getAllOrdersUsecase = GetAllOrdersUsecase()
            val list = getAllOrdersUsecase.execute()
            reduce {
                state.copy(listOfOrders = list)
            }
            postSideEffect(OrderSideEffect.ShowSuccess)
        }
    }

}
