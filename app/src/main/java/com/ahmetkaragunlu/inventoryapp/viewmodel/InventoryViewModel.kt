package com.ahmetkaragunlu.inventoryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetkaragunlu.inventoryapp.repository.InventoryRepo
import com.ahmetkaragunlu.inventoryapp.roomdb.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(private val repository: InventoryRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Item>>(emptyList())
    val uiState : StateFlow<List<Item>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.allItems().distinctUntilChanged().collect { itemList->
                _uiState.value = itemList
            }
        }
    }

    var username by mutableStateOf("")
    private set

    fun updateName(name : String) {
        username=name
    }

    var price by mutableStateOf("")
        private set

    fun updatePrice(newPrice : String) {
        price=newPrice
    }

    var quantity by mutableStateOf("")
        private set

    fun  updateQuantity(newQuantity : String) {
        quantity=newQuantity
    }


    fun add(item: Item) = viewModelScope.launch { repository.add(item) }
    fun delete(item: Item) = viewModelScope.launch { repository.delete(item) }
    fun update(item: Item) = viewModelScope.launch { repository.update(item) }

}