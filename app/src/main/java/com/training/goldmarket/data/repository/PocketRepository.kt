package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.data.entity.PocketType
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.entity.TransactionType

interface PocketRepository {
    fun getAllPocket(): List<Pocket>
    fun updatePocket(pocket: Pocket)
    fun insertNewPocket(name: String, type: PocketType): Pocket
    fun getAllTransaction(): List<Transaction>
    fun getAllPocketByUserId(userId: String): List<Pocket>
}