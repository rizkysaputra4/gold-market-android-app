package com.training.goldmarket.data.repository

import android.util.Log
import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.remote.api.TransactionApi
import com.training.goldmarket.data.remote.response.TransactionResponse
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val transactionApi: TransactionApi
    ): TransactionRepository {

    override suspend fun addTransaction(transaction: Transaction): Transaction? {
//        transactionDao.insert(transaction)
        val  response = transactionApi.postTransaction(transaction)
        if (response.isSuccessful) {
            Log.d("TRANSACTION", response.body().toString())
            return response.body()
        }
        return null
    }

    override suspend fun getUserTransaction(userId: String): List<Transaction>? {
        val response = transactionApi.getTransactionHistory(userId)
        if (response.isSuccessful) {
            Log.d("TRANSACTION", response.body().toString())
            return response.body()
        }
        return null
//        return transactionDao.getTransactionByUserId(userId)
    }
}