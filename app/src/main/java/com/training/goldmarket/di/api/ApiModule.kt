package com.training.goldmarket.di.api

import com.training.goldmarket.data.remote.api.PocketApi
import com.training.goldmarket.data.remote.api.UserApi
import com.training.goldmarket.data.remote.interceptor.AuthTokenInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8082/")
            .addConverterFactory(MoshiConverterFactory.create())
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
}