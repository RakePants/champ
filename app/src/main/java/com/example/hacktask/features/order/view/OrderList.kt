package com.example.hacktask.features.order.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.hacktask.R
import com.example.hacktask.features.order.presentation.OrderViewModel
import com.example.hacktask.model.Order
import com.example.hacktask.navigation.Screens
import com.example.hacktask.ui.theme.Description
import com.example.hacktask.ui.theme.Heading2
import com.example.hacktask.ui.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun OrderList(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navHostController: NavHostController,
) {

    val localState = viewModel.collectAsState()

    if (localState.value.listOfOrders.isEmpty()) {
        Log.d("Test", "OrderList: попал")
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier
                    .clickable {
                        navHostController.navigate(Screens.AddOrderScreen.route)
                },
                text = stringResource(id = R.string.no_order_yet),
                style = MaterialTheme.typography.Heading2,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    } else {

        LazyColumn(
            modifier = modifier
                .padding(top = MaterialTheme.dimens.gapBetweenComponents1)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
            )
        ) {
            items(items = localState.value.listOfOrders) { order ->
                OrderCard(navHostController = navHostController, id = order.id, address = order.address, type = order.type)
            }
        }
    }
}

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    id: Int,
    address: String,
    type: String,
) {
    Card(
        modifier = modifier
            .padding(bottom = MaterialTheme.dimens.gapBetweenComponents1)
            .fillMaxWidth()
            .clickable {
                navHostController.navigate(route = Screens.DescriptionScreen.route + "/$id")
            },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            modifier = Modifier.padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                top = MaterialTheme.dimens.gapBetweenComponentScreen
            ),
            text = "$address",
            style = MaterialTheme.typography.Heading2,
            maxLines = 1,

        )
        Spacer(modifier = Modifier.padding(bottom = MaterialTheme.dimens.bottomGap))
        Text(
            modifier = Modifier.padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                bottom = MaterialTheme.dimens.gapBetweenComponentScreen
            ),
            text = "$type",
            style = MaterialTheme.typography.Description,
        )
    }
}