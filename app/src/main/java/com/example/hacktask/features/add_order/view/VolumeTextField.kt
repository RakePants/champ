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
fun VolumeTextField(
    modifier: Modifier = Modifier,
    viewModel: AddOrderViewModel
) {
    TextFieldComponent(
        value = viewModel.volume.value,
        onValueChange = {
            viewModel.typingDescription(it)
            viewModel.volume.value = it
        },
        placeholder = stringResource(id = R.string.volume),
        singleLine = true,
        keyboardType = KeyboardType.Number
    )

//    var state by remember {
//        mutableStateOf("")
//    }
//    OutlinedTextField(
//        modifier = modifier
//            .height(MaterialTheme.dimens.buttonsHeight)
//            .fillMaxWidth()
//            .padding(
//                start = MaterialTheme.dimens.gapBetweenComponentScreen,
//                end = MaterialTheme.dimens.gapBetweenComponentScreen
//            ),
//        value = state,
//        onValueChange = { newValue ->
//            viewModel.typingDescription(newValue)
//            state = newValue
//        },
//        keyboardOptions = KeyboardOptions(
//            capitalization = KeyboardCapitalization.None,
//            autoCorrect = true,
//            keyboardType = KeyboardType.Number,
//            imeAction = ImeAction.Next
//        ),
//        textStyle = MaterialTheme.typography.TypingText,
//        singleLine = true,
//        placeholder = {
//            Text(
//                text = stringResource(id = R.string.volume_of_peet),
//                style = MaterialTheme.typography.TypingText,
//            )
//        },
//        shape = RoundedCornerShape(MaterialTheme.dimens.textFieldCornerRadius),
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
//            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
//            focusedContainerColor = Color.Transparent,
//            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
//            focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
//            focusedBorderColor = MaterialTheme.colorScheme.primary,
//            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
//            errorBorderColor = MaterialTheme.colorScheme.error,
//        ),
//    )
}