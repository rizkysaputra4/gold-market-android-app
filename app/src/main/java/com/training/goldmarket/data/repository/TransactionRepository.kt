package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.Transaction

interface TransactionRepository {

    fun addTransaction(transaction: Transaction)
    fun getUserTransaction(userId: String): List<Transaction>?
}