package com.training.intro.di

import com.training.intro.presentation.MainViewModel
import com.training.intro.presentation.history.HistoryViewModel
import com.training.intro.presentation.home.HomeViewModel
import com.training.intro.presentation.login.LoginViewModel
import com.training.intro.presentation.profile.ProfileViewModel
import com.training.intro.presentation.register.RegisterViewModel
import com.training.intro.repository.UserRepository
import com.training.intro.repository.PocketRepositoryImpl

class DependencyContainer {

    var pocketRepository = PocketRepositoryImpl()
    var userRepository= UserRepository()

    val loginViewModel = LoginViewModel(userRepository)
    val registerViewModel = RegisterViewModel(userRepository)
    val createPocketViewMode = HomeViewModel(pocketRepository)
    val historyViewModel = HistoryViewModel(pocketRepository)
    val mainViewModel = MainViewModel(userRepository, pocketRepository)
    val profileViewModel = ProfileViewModel(userRepository)
}