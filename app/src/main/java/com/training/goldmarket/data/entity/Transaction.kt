package com.training.goldmarket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.training.goldmarket.utils.AppConstant
import java.util.*

@Entity(tableName = "${AppConstant.TRANSACTION_TABLE}")
data class Transaction(

    @PrimaryKey
    var transactionId: String = UUID.randomUUID().toString(),

    var date: Date? = null,
    var type: TransactionType,
    var pocketName: String,
    var productPrice: Double,
    var productType: PocketType,
    var qty: Double,
    var transactionOwnerId: String?
) {
}