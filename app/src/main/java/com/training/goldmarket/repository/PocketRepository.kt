package com.training.goldmarket.repository

import com.training.goldmarket.model.*

interface PocketRepository {
    fun getPocketById(id: Int): Pocket
    fun getAllPocket(): List<Pocket>
    fun updatePocket(pocket: Pocket)
    fun insertNewPocket(name: String, type: PocketType): Pocket
    fun addTransaction(transactionType: TransactionType, productPrice: Double,
                       pocketName: String, pocketType: PocketType, qty: Double)
    fun getAllTransaction(): List<Transaction>
}