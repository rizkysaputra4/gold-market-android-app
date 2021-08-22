package com.training.goldmarket.di.api

import com.squareup.moshi.Moshi
import com.training.goldmarket.data.remote.api.PocketApi
import com.training.goldmarket.data.remote.api.TransactionApi
import com.training.goldmarket.data.remote.api.UserApi
import com.training.goldmarket.data.remote.interceptor.AuthTokenInterceptor
import com.training.goldmarket.utils.adapter.DateAdapter
import com.training.goldmarket.utils.adapter.PocketTypeAdapter
import com.training.goldmarket.utils.adapter.TransactionTypeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(authTokenInterceptor: AuthTokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authTokenInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {

        val moshiBuilder = Moshi.Builder()
            .add(DateAdapter())
            .add(TransactionTypeAdapter())
            .add(PocketTypeAdapter())

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8082/")
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun providePocketApi(retrofit: Retrofit): PocketApi {
        return retrofit.create(PocketApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi {
        return retrofit.create(TransactionApi::class.java)
    }
}

