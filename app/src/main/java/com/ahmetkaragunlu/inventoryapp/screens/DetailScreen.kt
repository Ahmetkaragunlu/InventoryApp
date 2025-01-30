package com.ahmetkaragunlu.inventoryapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.inventoryapp.R
import com.ahmetkaragunlu.inventoryapp.components.InventoryTopAppBar
import com.ahmetkaragunlu.inventoryapp.roomdb.Item
import java.text.NumberFormat


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    itemList: List<Item>,
    itemId: Int?,
    decQuantity: (Item) -> Unit,
    delete: (Item) -> Unit
) {
    val item = itemList.firstOrNull { id -> id.id == itemId }
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = R.string.item_details,
                canNavigateBack = true,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            if (item != null) {
                if (item.quantity > 0) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFe7e0eb))
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(text = item.name)
                                Row {
                                    Text(stringResource(id = R.string.quantity_stock))
                                    Spacer(modifier = modifier.weight(1f))
                                    Text(
                                        text = item.quantity.toString(),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Row {
                                    Text(stringResource(id = R.string.price))
                                    Spacer(modifier = modifier.weight(1f))
                                    Text(
                                        text = NumberFormat.getCurrencyInstance()
                                            .format(item.price),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        OutlinedButton(
                            onClick = {
                                decQuantity(item)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6b3dd4))
                        ) {
                            Text(
                                stringResource(R.string.sell),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                delete(item)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp),
                        ) {
                            Text(
                                stringResource(R.string.delete),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}