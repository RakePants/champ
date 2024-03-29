package com.example.hacktask.features.add_order.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hacktask.R
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.common.components.SmallTextFieldComponent
import com.example.hacktask.features.common.components.TextFieldComponent
import com.example.hacktask.ui.theme.TypingText
import com.example.hacktask.ui.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun LongitudeTextField(
    modifier: Modifier = Modifier,
    viewModel: AddOrderViewModel,
) {
    if (viewModel.collectAsState().value.lattitude != 0.0) {
        SmallTextFieldComponent(
            modifier = modifier.padding(end = MaterialTheme.dimens.gapBetweenComponentScreen),
            onValueChange = {
                viewModel.typingLongitude(it)
                viewModel.longitude.value = it.toDouble()
            },
            value = viewModel.longitude.value.toString(),
            placeholder = stringResource(id = R.string.longitude),
            singleLine = true,
            keyboardType = KeyboardType.Number,
        )
    }
    else {
        SmallTextFieldComponent(
            modifier = modifier.padding(end = MaterialTheme.dimens.gapBetweenComponentScreen),
            onValueChange = {
                viewModel.typingLongitude(it)
                viewModel.longitude.value = it.toDouble()
            },
            value = "",
            placeholder = stringResource(id = R.string.longitude),
            singleLine = true,
            keyboardType = KeyboardType.Number,
        )
    }

}