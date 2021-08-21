package com.training.goldmarket.data.repository

import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.entity.Transaction
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao): TransactionRepository {

    override suspend fun addTransaction(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    override suspend fun getUserTransaction(userId: String): List<Transaction>? {
        return transactionDao.getTransactionByUserId(userId)
    }
}