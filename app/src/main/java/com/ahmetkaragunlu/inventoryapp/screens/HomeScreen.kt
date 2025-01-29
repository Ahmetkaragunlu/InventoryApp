package com.ahmetkaragunlu.inventoryapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.inventoryapp.R
import com.ahmetkaragunlu.inventoryapp.components.InventoryTopAppBar
import com.ahmetkaragunlu.inventoryapp.navigation.Screens
import com.ahmetkaragunlu.inventoryapp.roomdb.Item
import java.text.NumberFormat
import java.util.Locale


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    itemList: List<Item>,
    navController: NavController
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = R.string.inventory,
                canNavigateBack = false,
                navController=navController
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = modifier.fillMaxWidth()) {
                if (itemList.isEmpty()) {
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    LazyColumn {
                        items(itemList) { item ->
                            Card(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFe7e0eb))
                            ) {
                                Column(
                                    modifier = modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Spacer(modifier = modifier.weight(1f))
                                        Text(
                                            text = NumberFormat.getCurrencyInstance(Locale.getDefault())
                                                .format(item.price),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Text(
                                        text = stringResource(
                                            id = item.quantity,
                                            R.string.in_stock
                                        ),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }

                }
            }
            Spacer(modifier = modifier.weight(1f))
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .background(
                        color = Color(0xFFe7e0eb),
                        shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp)
                    )
                    .align(Alignment.End),
            ) {
                IconButton(
                    onClick = { navController.navigate(Screens.AddItemScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                }
            }

        }
    }

}