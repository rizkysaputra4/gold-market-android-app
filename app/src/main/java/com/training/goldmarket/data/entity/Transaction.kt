package com.training.goldmarket.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.training.goldmarket.utils.AppConstant
import java.util.*

@Entity(tableName = "${AppConstant.TRANSACTION_TABLE}")
data class Transaction(

    @PrimaryKey
    var transactionId: String = UUID.randomUUID().toString(),
    val date: Date,
    val type: TransactionType,
    val pocketName: String,
    val productPrice: Double,
    val productType: PocketType,
    val qty: Double,
    var transactionOwnerId: String?
) {
}