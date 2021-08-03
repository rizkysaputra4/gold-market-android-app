package com.training.goldmarket.di

import com.training.goldmarket.presentation.MainViewModel
import com.training.goldmarket.presentation.history.HistoryViewModel
import com.training.goldmarket.presentation.home.HomeViewModel
import com.training.goldmarket.presentation.login.LoginViewModel
import com.training.goldmarket.presentation.profile.ProfileViewModel
import com.training.goldmarket.presentation.register.RegisterViewModel
import com.training.goldmarket.presentation.welcome.WelcomeViewModel
import com.training.goldmarket.repository.UserRepository
import com.training.goldmarket.repository.PocketRepositoryImpl

class DependencyContainer {

    var pocketRepository = PocketRepositoryImpl()
    var userRepository= UserRepository()

    val loginViewModel = LoginViewModel(userRepository)
    val registerViewModel = RegisterViewModel(userRepository)
    val createPocketViewMode = HomeViewModel(pocketRepository)
    val historyViewModel = HistoryViewModel(pocketRepository)
    val mainViewModel = MainViewModel(userRepository, pocketRepository)
    val profileViewModel = ProfileViewModel(userRepository)
    val welcomeViewModel = WelcomeViewModel(userRepository)
}