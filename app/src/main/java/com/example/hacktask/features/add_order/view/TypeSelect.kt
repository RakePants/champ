package com.example.hacktask.features.add_order.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.ui.theme.TypingText
import com.example.hacktask.ui.theme.dimens

@Composable
fun TypeSelect(
    viewModel: AddOrderViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        "Cтирание краски", "Cминание", "Изгибание", "Нарушение целостности дорожного знака",
        "Вандализм (граффити, надписи, наклейки, следы краски, отверстия)", "Перекрытие растительностью", "Снежный налет", "Ремонт",
        "Разрушение", "Продольная трещина", "Яма", "Трещина поперечная", "Cтирание разметки", "Размытие пешеходного перехода", "Разрушение бордюрного камня",
        "Железная балка", "Бетонный блок", "Железный забор", "Тросовый забор"
        )

    var tempText by remember {
        mutableStateOf("Выберите тип")
    }

    Column {
        Box(
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.dimens.gapBetweenComponentScreen,
                    end = MaterialTheme.dimens.gapBetweenComponentScreen,
                )
                .height(MaterialTheme.dimens.buttonsHeight)
                .border(
                    MaterialTheme.dimens.buttonsWidth,
                    MaterialTheme.colorScheme.onPrimary,
                    RoundedCornerShape(MaterialTheme.dimens.textFieldCornerRadius)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = tempText,
                style = MaterialTheme.typography.TypingText,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.dimens.gapBetweenComponentScreen,
                    end = MaterialTheme.dimens.gapBetweenComponentScreen,
                ),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        // Действия при выборе элемента списка
                        tempText = item
                        viewModel.chooseType(item)
                        viewModel.type.value = item
                    },
                    text = {
                        Text(item)
                    },
                )


            }
        }
    }
}

//@Preview
//@Composable
//fun TypeSelectPreview() {
//    TypeSelect()
//}