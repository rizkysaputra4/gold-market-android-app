package com.training.goldmarket.data.entity

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

enum class TransactionType(val value: String) {

    @Json(name = "Buy")
    @SerializedName("Buy")
    Buy("Buy"),

    @Json(name = "Sell")
    @SerializedName("Sell")
    Sell("Sell")
}