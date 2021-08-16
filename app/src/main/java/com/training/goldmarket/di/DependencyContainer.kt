package com.training.goldmarket.di

import android.content.Context
import com.training.goldmarket.data.db.AppDatabase
import com.training.goldmarket.data.db.PocketDao
import com.training.goldmarket.data.db.TransactionDao
import com.training.goldmarket.data.db.UserDao
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.data.repository.PocketRepository
import com.training.goldmarket.presentation.MainViewModel
import com.training.goldmarket.presentation.history.HistoryViewModel
import com.training.goldmarket.presentation.home.HomeViewModel
import com.training.goldmarket.presentation.login.LoginViewModel
import com.training.goldmarket.presentation.profile.ProfileViewModel
import com.training.goldmarket.presentation.register.RegisterViewModel
import com.training.goldmarket.presentation.welcome.WelcomeViewModel
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.data.repository.PocketRepositoryImpl
import com.training.goldmarket.data.repository.TransactionRepository

class DependencyContainer {

    lateinit var sharedPreference: SharedPreference

    lateinit var userDao: UserDao
    lateinit var pocketDao: PocketDao
    lateinit var transactionDao: TransactionDao

    lateinit var pocketRepository: PocketRepository
    lateinit var userRepository: UserRepository
    lateinit var transactionRepository: TransactionRepository

    lateinit var loginViewModel: LoginViewModel
    lateinit var registerViewModel: RegisterViewModel
    lateinit var createPocketViewMode: HomeViewModel
    lateinit var historyViewModel: HistoryViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var profileViewModel: ProfileViewModel
    lateinit var welcomeViewModel: WelcomeViewModel

    fun initRepository(context: Context) {
        this.sharedPreference = SharedPreference(context)

        this.userDao = AppDatabase.getDatabase(context).userDao()
        this.pocketDao = AppDatabase.getDatabase(context).pocketDao()
        this.transactionDao = AppDatabase.getDatabase(context).transactionDao()

        this.userRepository = UserRepository(this.userDao)
        this.pocketRepository = PocketRepositoryImpl(this.pocketDao, this.userRepository)
        this.transactionRepository = TransactionRepository(this.transactionDao)

        this.loginViewModel = LoginViewModel(userRepository, sharedPreference)
        this.registerViewModel = RegisterViewModel(userRepository)
        this.createPocketViewMode = HomeViewModel(pocketRepository, transactionRepository, userRepository)
        this.historyViewModel = HistoryViewModel(transactionRepository, userRepository)
        this.mainViewModel = MainViewModel(userRepository, pocketRepository)
        this.profileViewModel = ProfileViewModel(userRepository, sharedPreference)
        this.welcomeViewModel = WelcomeViewModel(userRepository, sharedPreference)
    }
}