package com.training.goldmarket.data.remote.request

data class NewPocketRequest(
    val pocketQty: Int,
    val pocketName: String,
    val customer: Customer,
    val product: Product
)

data class Customer(
    var id: String
)

data class Product(
    var id: Int
)
