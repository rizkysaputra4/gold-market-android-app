package com.training.intro.model

class Pocket(
    val id: Int,
    val name: String,
    val product: Product,
    var qty: Double
) {
}

enum class PocketType {
    Gold, Silver, Platinum
}