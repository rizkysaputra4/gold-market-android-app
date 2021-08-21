package com.training.goldmarket.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class Product(

    @PrimaryKey
    var productId: Int = 1,
    val type: PocketType,
    val priceBuy: Double,
    val priceSell: Double,
) {
}