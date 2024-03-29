package com.example.hacktask.features.add_order.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.hacktask.R
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.common.components.TextFieldComponent

@Composable
fun AddressTextField(
    modifier: Modifier = Modifier,
    viewModel: AddOrderViewModel,
) {
    TextFieldComponent(
        value = viewModel.address.value,
        onValueChange = {
            viewModel.typingAddress(it)
            viewModel.address.value = it
        },
        placeholder = stringResource(id = R.string.address),
        singleLine = true,
    )
}