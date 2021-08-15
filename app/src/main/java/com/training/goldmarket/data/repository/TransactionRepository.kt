package com.training.goldmarket.data.repository

import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.entity.Transaction

class TransactionRepository(private val transactionDao: TransactionDao) {

    fun addTransaction(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    fun getUserTransaction(userId: String): List<Transaction>? {
        return transactionDao.getTransactionByUserId(userId)
    }
}