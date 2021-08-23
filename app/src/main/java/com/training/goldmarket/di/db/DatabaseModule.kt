package com.training.goldmarket.di.db

import android.app.Application
import androidx.room.Room
import com.training.goldmarket.data.db.AppDatabase
import com.training.goldmarket.data.db.PocketDao
import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.db.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providerRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "gold-market_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providerUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun providerTransactionDao(db: AppDatabase): TransactionDao {
        return db.transactionDao()
    }

    @Provides
    @Singleton
    fun providerPocketDao(db: AppDatabase): PocketDao {
        return db.pocketDao()
    }
}