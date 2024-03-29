package com.example.hacktask.features.order_description

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.hacktask.R
import com.example.hacktask.features.order_description.presentation.OrderDescriptionScreenState
import com.example.hacktask.features.order_description.presentation.OrderDescriptionViewModel
import com.example.hacktask.features.order_description.view.AddressText
import com.example.hacktask.features.order_description.view.DateCard1
import com.example.hacktask.features.order_description.view.DateCard2
import com.example.hacktask.features.order_description.view.DescriptionText
import com.example.hacktask.features.order_description.view.ImageCard
import com.example.hacktask.features.order_description.view.LatitudeText
import com.example.hacktask.features.order_description.view.LoadingProgressBar
import com.example.hacktask.features.order_description.view.LongitudeText
import com.example.hacktask.features.order_description.view.TypeText
import com.example.hacktask.features.order_description.view.VolumeText
import com.example.hacktask.ui.theme.Heading1
import com.example.hacktask.ui.theme.Heading2
import com.example.hacktask.ui.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun OrderDescriptionBasic(
    viewModel: OrderDescriptionViewModel,
    navHostController: NavHostController,
) {
    val localState = viewModel.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (viewModel.screenState.value) {
            OrderDescriptionScreenState.Error -> {

            }
            OrderDescriptionScreenState.Loading -> {
                LoadingProgressBar()
            }
            OrderDescriptionScreenState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    ImageCard()
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        DateCard1(viewModel = viewModel, date = localState.value.timestamp)
                        if (localState.value.completionTimestamp != null) {
                            DateCard2(viewModel = viewModel, date = localState.value.completionTimestamp!!)
                        }
                        else {
                            DateCard2(viewModel = viewModel, date = "-")
                        }
                    }
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    AddressText(text2 = localState.value.address)
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    LatitudeText(text2 = localState.value.latitude.toString())
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    LongitudeText(text2 = localState.value.longitude.toString())
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    TypeText(text2 = localState.value.type)
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    VolumeText(text2 = localState.value.volume.toString())
                    Spacer(modifier = Modifier.padding(MaterialTheme.dimens.bottomGap))
                    DescriptionText(text2 = localState.value.description)
                }
            }
        }
    }

}