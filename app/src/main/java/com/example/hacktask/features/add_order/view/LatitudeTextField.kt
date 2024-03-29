package com.example.hacktask.features.add_order.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.hacktask.R
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.common.components.SmallTextFieldComponent
import com.example.hacktask.ui.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun LatitudeTextField(
    modifier: Modifier = Modifier,
    viewModel: AddOrderViewModel,
) {
    if (viewModel.collectAsState().value.lattitude != null) {
        SmallTextFieldComponent(
            modifier = modifier.padding(start = MaterialTheme.dimens.gapBetweenComponentScreen),
            value = viewModel.lattitude.value.toString(),
            onValueChange = {
                viewModel.typingLatitude(it)
                viewModel.lattitude.value = it.toDouble()
            },
            placeholder = stringResource(id = R.string.latitude),
            singleLine = true,
            keyboardType = KeyboardType.Number
        )
    }
    else {
        SmallTextFieldComponent(
            modifier = modifier.padding(start = MaterialTheme.dimens.gapBetweenComponentScreen),
            value = viewModel.lattitude.value.toString(),
            onValueChange = {
                viewModel.typingLatitude(it)
                viewModel.lattitude.value = it.toDouble()
            },
            placeholder = stringResource(id = R.string.latitude),
            singleLine = true,
            keyboardType = KeyboardType.Number
        )
    }

}

