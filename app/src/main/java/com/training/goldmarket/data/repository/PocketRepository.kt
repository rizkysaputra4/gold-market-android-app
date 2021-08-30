package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.data.entity.PocketType
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.entity.TransactionType

interface PocketRepository {
    suspend fun updatePocket(pocket: Pocket): Boolean
    suspend fun insertNewPocket(name: String, type: PocketType): Pocket?
    suspend fun getAllPocketByUserId(userId: String): List<Pocket>
    suspend fun deletePocket(id: String): Boolean
}