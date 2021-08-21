package com.training.goldmarket.di.repository

import com.training.goldmarket.data.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providerPocketRepository(pocketRepositoryImpl: PocketRepositoryImpl): PocketRepository

    @Binds
    @Singleton
    abstract fun providerTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Singleton
    abstract fun providerUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}