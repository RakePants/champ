package com.example.hacktask.features.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.hacktask.features.order.presentation.OrderScreenState
import com.example.hacktask.features.order.presentation.OrderViewModel
import com.example.hacktask.features.order.view.LoadingProgressBar
import com.example.hacktask.features.order.view.LogoImage
import com.example.hacktask.features.order.view.LogoText
import com.example.hacktask.features.order.view.OrderList
import com.example.hacktask.navigation.Screens
import com.example.hacktask.ui.theme.dimens

@Composable
fun OrderBasic(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel,
    navHostController: NavHostController,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (viewModel.screenState.value) {
            is OrderScreenState.Loading -> {
                Surface(
                    modifier = modifier
                        .fillMaxSize(),
                        color = MaterialTheme.colorScheme.primary,
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LogoImage()
                        Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents1))
                        LogoText()
                        Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.gapBetweenComponents2))
                        LoadingProgressBar()
                    }
                }

            }
            is OrderScreenState.Error -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Произошла ошибка")
                }
            }
            is OrderScreenState.Success -> {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navHostController.navigate(Screens.AddOrderScreen.route) },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            content = {
                                Icon(Icons.Rounded.Add,"")
                            }
                        )
                    },
                    content = {
                        Surface(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            Column(
                                modifier = modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                OrderList(
                                    viewModel = viewModel,
                                    navHostController = navHostController,
                                )
                            }
                        }
                    }
                )
            }
        }
    }


}