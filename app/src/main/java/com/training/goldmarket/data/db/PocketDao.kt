package com.training.goldmarket.data.db

import androidx.room.Dao
import androidx.room.Query
import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.utils.AppConstant

@Dao
interface PocketDao: BaseDao<Pocket> {

    @Query("SELECT * FROM ${AppConstant.POCKET_TABLE}")
    fun getAllPocket(): List<Pocket>

    @Query("SELECT * FROM ${AppConstant.POCKET_TABLE} WHERE pocketOwnerId=:userId")
    fun getAllPocketByUserId(userId: String): List<Pocket>
}