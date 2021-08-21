package com.training.goldmarket.data.repository

import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.entity.Transaction
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao): TransactionRepository {

    override fun addTransaction(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    override fun getUserTransaction(userId: String): List<Transaction>? {
        return transactionDao.getTransactionByUserId(userId)
    }
}