package com.example.hacktask.features.add_order.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.hacktask.R
import com.example.hacktask.data.model.OrderEntity
import com.example.hacktask.data.repository.OrderRepositoryImpl
import com.example.hacktask.domain.usecases.CreateOrderUsecase
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.common.components.ButtonComponent1
import com.example.hacktask.model.Order
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CreateBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    viewModel: AddOrderViewModel,
    navHostController: NavHostController,
) {
    val localState = viewModel.collectAsState().value
    ButtonComponent1(
        onClick = {
            //todo добавить запрос в api
            //onClick()
            viewModel.sendResponseOrder()
            navHostController.popBackStack()

            Log.d("test", "CreateBtn: ${localState.address} ${localState.description} ${localState.lattitude} ${localState.longitude} ${localState.volume}")
        },
        text = stringResource(id = R.string.create)
    )
}