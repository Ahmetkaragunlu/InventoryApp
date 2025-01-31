package com.ahmetkaragunlu.inventoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmetkaragunlu.inventoryapp.screens.AddItemScreen
import com.ahmetkaragunlu.inventoryapp.screens.DetailScreen
import com.ahmetkaragunlu.inventoryapp.screens.EditItemScreen
import com.ahmetkaragunlu.inventoryapp.screens.HomeScreen
import com.ahmetkaragunlu.inventoryapp.viewmodel.InventoryViewModel


@Composable
fun InventoryNavigation(viewModel: InventoryViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                itemList = uiState,
                navController = navController
            )
        }
        composable(route = Screens.AddItemScreen.route) {
            AddItemScreen(
                navController = navController,
                quantity = viewModel.inputQuantity,
                price = viewModel.inputPrice,
                userName = viewModel.inputUsername,
                setName = { viewModel.updateName(it) },
                setPrice = { viewModel.updatePrice(it) },
                setQuantity = { viewModel.updateQuantity(it) },
                saveItem = { name, quantity, price -> viewModel.saveItem(name, quantity, price) },
                clearItem = { viewModel.clearItem() }
            )
        }
        composable(
            route = "DetailScreen/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            DetailScreen(
                navController = navController,
                itemId = itemId,
                itemList = uiState,
                decQuantity = { viewModel.decQuantity(it) },
                delete = { viewModel.delete(it) }
            )
        }
        composable(
            route = "EditItemScreen/{itemId}",
            arguments = listOf(navArgument(name = "itemId"){type= NavType.IntType})
        ) { backStackEntry->
            val itemId= backStackEntry.arguments?.getInt("itemId")
            EditItemScreen(
                navController = navController,
                itemId = itemId,
                itemList = uiState,
                editItem = {id,name,quantity,price->viewModel.editItem(id,name,quantity,price)}
            )
        }
    }
}