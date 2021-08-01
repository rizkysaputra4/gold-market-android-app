package com.training.intro.di

import com.training.intro.presentation.MainViewModel
import com.training.intro.presentation.history.HistoryViewModel
import com.training.intro.presentation.home.HomeViewModel
import com.training.intro.presentation.login.LoginViewModel
import com.training.intro.presentation.register.RegisterViewModel
import com.training.intro.repository.UserRepository
import com.training.intro.repository.PocketRepositoryImpl

class DependencyContainer {

    private val pocketRepository = PocketRepositoryImpl()
    private val userRepository= UserRepository()

    val loginViewModel = LoginViewModel(userRepository)
    val registerViewModel = RegisterViewModel(userRepository)
    val createPocketViewMode = HomeViewModel(pocketRepository)
    val historyViewModel = HistoryViewModel(pocketRepository)
    val mainViewModel = MainViewModel(userRepository)
}