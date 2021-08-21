package com.training.goldmarket.data.remote.response

data class GetUserResponse(
    var id: String,
    var pocketName: String,
    var pocketQty: Double,
    var customerId: String,
    var productId: Int
)
