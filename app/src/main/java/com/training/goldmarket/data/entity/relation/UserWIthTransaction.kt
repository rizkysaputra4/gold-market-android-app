package com.training.goldmarket.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.entity.User

data class UserWIthTransaction(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "pocketId",
        entityColumn = "transactionOwnerId"
    )
    val transactions: List<Transaction>
)
