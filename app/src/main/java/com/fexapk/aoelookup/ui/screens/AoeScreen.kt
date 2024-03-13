package com.fexapk.aoelookup.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fexapk.aoelookup.R
import com.fexapk.aoelookup.ui.SearchViewModel

enum class AoeApp {
    Search,
    PlayerStats
}

@Composable
fun AoeLookupApp(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AoeApp.Search.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.list_spacing))
    ) {
        composable(route = AoeApp.Search.name) {
            SearchScreen(
                viewModel = viewModel,
                Modifier.fillMaxSize(),
                onPlayerCardClick = {
                    viewModel.selectedPlayer = it
                    navController.navigate(AoeApp.PlayerStats.name)
                }
            )
        }
        composable(route = AoeApp.PlayerStats.name) {
            StatsScreen(
                player = viewModel.selectedPlayer,
                navigateBack = { navController.navigate(AoeApp.Search.name) }
            )
        }
    }
}


