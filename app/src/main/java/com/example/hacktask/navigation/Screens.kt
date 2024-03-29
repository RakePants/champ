package com.example.hacktask.navigation

import com.example.hacktask.utils.Constants

sealed class Screens(val route: String) {
    object OrderScreen: Screens(Constants.ORDER_SCREEN)
    object AddOrderScreen: Screens(Constants.ADD_ORDER_SCREEN)
    object DescriptionScreen: Screens(Constants.DESCRIPTION_SCREEN)
}