package com.training.intro.model

import java.util.*

class Transaction(
    val id: Int,
    val date: Date,
    val type: TransactionType,
    val pocketName: String,
    val productPrice: Double,
    val productType: PocketType,
    val qty: Double
) {
}

enum class TransactionType {
    Buy, Sell
}