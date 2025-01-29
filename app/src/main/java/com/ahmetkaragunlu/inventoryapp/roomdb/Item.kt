package com.ahmetkaragunlu.inventoryapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "Name")
    val name : String,

    @ColumnInfo(name = "Price")
    val price : Int,

    @ColumnInfo(name = "Quantity")
    val quantity : Int
)
