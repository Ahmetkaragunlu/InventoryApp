package com.ahmetkaragunlu.inventoryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetkaragunlu.inventoryapp.repository.InventoryRepo
import com.ahmetkaragunlu.inventoryapp.roomdb.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(private val repository: InventoryRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Item>>(emptyList())
    val uiState: StateFlow<List<Item>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allItems().collect { itemList ->
                _uiState.value = itemList
            }
        }
    }

    var username by mutableStateOf("")
        private set

    fun updateName(name: String) {
        username = name
    }

    var price by mutableStateOf("")
        private set

    fun updatePrice(newPrice: String) {
        price = newPrice
    }

    var quantity by mutableStateOf("")
        private set

    fun updateQuantity(newQuantity: String) {
        quantity = newQuantity
    }

    fun saveItem(name: String, quantity: String, price: String) {
        val newItem = Item(
            name = name,
            quantity = quantity.toIntOrNull() ?: 0,
            price = price.toDoubleOrNull() ?: 0.0
        )
        add(newItem)
    }

    fun clearItem() {
        username = ""
        this.price = ""
        this.quantity = ""
    }

    fun decQuantity(item: Item) {
        val newItem = item.copy(quantity = item.quantity - 1)
        if (newItem.quantity == 0) {
            delete(newItem)
        } else {
            update(newItem)
        }
    }

    private fun add(item: Item) = viewModelScope.launch { repository.add(item) }
    fun delete(item: Item) = viewModelScope.launch { repository.delete(item) }
    private fun update(item: Item) = viewModelScope.launch { repository.update(item) }
}