package com.training.goldmarket.data.entity

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

enum class PocketType(val value: String) {

    @SerializedName("Gold")
    @Json(name = "Gold")
    Gold("Gold"),

    @Json(name = "Silver")
    @SerializedName("Silver")
    Silver("Silver"),

    @Json(name = "Platinum")
    @SerializedName("Platinum")
    Platinum("Platinum")
}