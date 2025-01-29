package com.ahmetkaragunlu.inventoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ahmetkaragunlu.inventoryapp.navigation.InventoryNavigation
import com.ahmetkaragunlu.inventoryapp.ui.theme.InventoryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryAppTheme {
             InventoryNavigation()
            }
        }
    }
}
