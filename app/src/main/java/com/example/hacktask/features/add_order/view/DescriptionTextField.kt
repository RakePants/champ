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
import com.example.hacktask.R
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.common.components.TextFieldComponent
import com.example.hacktask.ui.theme.TypingText
import com.example.hacktask.ui.theme.dimens



@Composable
fun DescriptionTextField(
    modifier: Modifier = Modifier,
    viewModel: AddOrderViewModel
) {
    TextFieldComponent(
        value = viewModel.description.value,
        onValueChange = {
            viewModel.typingDescription(it)
            viewModel.description.value = it
        },
        placeholder = stringResource(id = R.string.description),
        singleLine = true,
    )
}