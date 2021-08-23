package com.training.goldmarket.data.entity

import androidx.room.PrimaryKey

data class Product(

    @PrimaryKey
    var productId: Int = 1,
    val type: PocketType,
    val priceBuy: Double,
    val priceSell: Double,
) {
}