package com.training.goldmarket.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Pocket(

    @PrimaryKey
    var pocketId: String = UUID.randomUUID().toString(),
    val name: String,

    @Embedded
    var product: Product,

    @ColumnInfo(name = "qty")
    var qty: Double,
    val pocketOwnerId: String?
) {
}