package com.training.goldmarket.data.remote.request

data class EditPocketRequest(
    val id: String,
    val pocketQty: Int,
    val pocketName: String,
    val customer: Customer,
    val product: Product
)
