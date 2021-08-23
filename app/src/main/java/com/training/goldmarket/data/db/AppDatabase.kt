package com.training.goldmarket.data.db

import android.content.Context
import androidx.room.*
import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.entity.User

@Database(entities = [User::class, Pocket::class, Transaction::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
    abstract fun pocketDao(): PocketDao

}