package com.ahmetkaragunlu.inventoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmetkaragunlu.inventoryapp.screens.AddItemScreen
import com.ahmetkaragunlu.inventoryapp.screens.HomeScreen
import com.ahmetkaragunlu.inventoryapp.viewmodel.InventoryViewModel


@Composable
fun InventoryNavigation(viewModel: InventoryViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(navController=navController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
           HomeScreen(
               itemList = uiState,
               navController = navController
           )
        }
        composable(route = Screens.AddItemScreen.route) {
           AddItemScreen(
               navController = navController,
               quantity = viewModel.quantity,
               price = viewModel.price,
               userName = viewModel.username,
               setName = {viewModel.updateName(it)},
               setPrice = {viewModel.updatePrice(it)},
               setQuantity = {viewModel.updateQuantity(it)},
           )
        }
        composable(route = Screens.EditItemScreen.route) {

        }
        composable(route = Screens.DetailScreen.route) {

        }
    }

}