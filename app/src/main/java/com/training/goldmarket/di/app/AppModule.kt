package com.training.goldmarket.di.app

import android.app.Application
import androidx.room.Room
import androidx.room.Transaction
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.training.goldmarket.data.db.AppDatabase
import com.training.goldmarket.data.db.PocketDao
import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.db.UserDao
import com.training.goldmarket.data.preference.SharedPreference
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3008/enigma/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}