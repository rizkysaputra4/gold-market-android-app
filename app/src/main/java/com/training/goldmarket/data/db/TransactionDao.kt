package com.training.goldmarket.data.db

import androidx.room.Dao
import androidx.room.Query
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.utils.AppConstant

@Dao
interface TransactionDao: BaseDao<Transaction> {

    @Query("SELECT * FROM ${AppConstant.TRANSACTION_TABLE} WHERE transactionOwnerId=:userId")
    fun getTransactionByUserId(userId: String): List<Transaction>?
}