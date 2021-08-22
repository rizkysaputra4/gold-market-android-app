package com.training.goldmarket.data.remote.response

import androidx.room.PrimaryKey
import com.training.goldmarket.data.entity.PocketType
import com.training.goldmarket.data.entity.TransactionType
import java.util.*

data class TransactionResponse(

    var transactionId: String?,
    var date: Date,
    var type: TransactionType,
    var pocketName: String,
    var productPrice: Double,
    var productType: PocketType,
    var qty: Double,
    var transactionOwnerId: String?
) {
}
