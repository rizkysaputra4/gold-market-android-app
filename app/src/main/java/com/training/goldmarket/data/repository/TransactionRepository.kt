package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.remote.response.TransactionResponse

interface TransactionRepository {

    suspend fun addTransaction(transaction: Transaction): Transaction?
    suspend fun getUserTransaction(userId: String): List<Transaction>?
}