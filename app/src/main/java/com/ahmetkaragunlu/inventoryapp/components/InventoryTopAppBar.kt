package com.ahmetkaragunlu.inventoryapp.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    @StringRes title : Int,
    canNavigateBack : Boolean,
     navController: NavController
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
    )
}

