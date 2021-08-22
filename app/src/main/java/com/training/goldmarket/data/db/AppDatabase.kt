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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "goldmarket_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}