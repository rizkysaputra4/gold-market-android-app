package com.training.goldmarket.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj: T)

    @Delete
    fun delete(obj: T)
}