package com.example.cleanarchitecture.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.cleanarchitecture.feature.main.MainRoute


const val mainNavigationRoute = "main"
private const val DEEP_LINK_BASE_URL = "https://com.potatomadness.clean"
private const val DEEP_LINK_URI_PATTERN = "{$DEEP_LINK_BASE_URL}/first"
fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    this.navigate(mainNavigationRoute, navOptions)
}

fun NavGraphBuilder.mainScreen() {
    composable(
        route = mainNavigationRoute,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        )
    ) {
        MainRoute()
    }
}
