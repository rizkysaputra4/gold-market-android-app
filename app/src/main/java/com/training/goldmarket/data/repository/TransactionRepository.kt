package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.Transaction

interface TransactionRepository {

    suspend fun addTransaction(transaction: Transaction)
    suspend fun getUserTransaction(userId: String): List<Transaction>?
}