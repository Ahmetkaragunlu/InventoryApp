package com.ahmetkaragunlu.inventoryapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahmetkaragunlu.inventoryapp.R
import com.ahmetkaragunlu.inventoryapp.components.InventoryTopAppBar
import com.ahmetkaragunlu.inventoryapp.components.ItemTextField
import com.ahmetkaragunlu.inventoryapp.navigation.Screens
import java.util.Currency
import java.util.Locale


@SuppressLint("RestrictedApi")
@Composable
fun AddItemScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    quantity: String,
    price: String,
    userName: String,
    setName: (String) -> Unit,
    setPrice: (String) -> Unit,
    setQuantity: (String) -> Unit,
    saveItem: (String, String, String) -> Unit,
    clearItem: () -> Unit
) {
    val currentScreens = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentScreens.value?.destination?.route) {
        if (currentScreens.value?.destination?.route != Screens.AddItemScreen.route) {
            clearItem()
        }
    }
    val currencySymbol = Currency.getInstance(Locale.getDefault()).symbol
    Scaffold(topBar = {
        InventoryTopAppBar(
            title = R.string.add_item,
            canNavigateBack = true,
            navController = navController,
        )
    }) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ItemTextField(
                value = userName,
                onValueChange = { setName(it) },
                label = R.string.item_name,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
                )
            )
            ItemTextField(value = price,
                onValueChange = { setPrice(it) },
                label = R.string.item_price,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
                ),
                leadingIcon = { Text(text = currencySymbol) })
            ItemTextField(
                value = quantity,
                onValueChange = { setQuantity(it) },
                label = R.string.quantity_stock,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    saveItem(userName, quantity, price)
                    navController.navigateUp()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
                enabled = quantity.isNotBlank() && price.isNotBlank() && userName.isNotBlank(),
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}