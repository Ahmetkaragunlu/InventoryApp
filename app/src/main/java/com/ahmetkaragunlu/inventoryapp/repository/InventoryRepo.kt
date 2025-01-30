package com.ahmetkaragunlu.inventoryapp.repository

import com.ahmetkaragunlu.inventoryapp.roomdb.InventoryDao
import com.ahmetkaragunlu.inventoryapp.roomdb.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InventoryRepo  @Inject constructor(private val inventoryDao: InventoryDao) {

    suspend fun add(item: Item) = inventoryDao.insert(item)
    suspend fun delete(item: Item) = inventoryDao.delete(item)
    suspend fun update(item: Item) = inventoryDao.update(item)
    fun allItems() : Flow<List<Item>> = inventoryDao.getAllItems().flowOn(Dispatchers.IO).distinctUntilChanged()
}