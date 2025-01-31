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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.inventoryapp.R
import com.ahmetkaragunlu.inventoryapp.components.InventoryTopAppBar
import com.ahmetkaragunlu.inventoryapp.components.ItemTextField
import com.ahmetkaragunlu.inventoryapp.roomdb.Item
import java.util.Currency
import java.util.Locale


@SuppressLint("RestrictedApi")
@Composable
fun EditItemScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    itemId: Int?,
    itemList: List<Item>,
    editItem: (Int, String, String, String) -> Unit
) {
    val item = itemList.firstOrNull { it.id == itemId }
    var name by rememberSaveable { mutableStateOf(item?.name ?: "") }
    var price by rememberSaveable { mutableStateOf(item?.price?.toString() ?: "") }
    var quantity by rememberSaveable { mutableStateOf(item?.quantity?.toString() ?: "") }
    val currencySymbol = Currency.getInstance(Locale.getDefault()).symbol

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = R.string.item_details,
                canNavigateBack = true,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding).verticalScroll(rememberScrollState())) {
            if (item != null) {
                ItemTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = R.string.item_name,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    )
                )
                ItemTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = R.string.item_price,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    leadingIcon = { Text(text = currencySymbol) }
                )
                ItemTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = R.string.quantity_stock,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    )
                )
                Button(
                    onClick = {
                        editItem(item.id, name, quantity, price)
                        navController.navigateUp()
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
                    enabled = quantity.isNotBlank() && price.isNotBlank() && name.isNotBlank(),

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
}