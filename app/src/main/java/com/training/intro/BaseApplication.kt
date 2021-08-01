package com.training.intro

import android.app.Application
import com.training.intro.di.DependencyContainer
import com.training.intro.repository.PocketRepository
import com.training.intro.repository.PocketRepositoryImpl
import com.training.intro.repository.UserRepository

class BaseApplication: Application() {

    private val container: DependencyContainer by lazy {
        DependencyContainer()
    }

    fun getLoginViewModel() = container.loginViewModel
    fun getregisterViewModel() = container.registerViewModel
    fun getCreatePocketViewModel() = container.createPocketViewMode
    fun getHistoryViewModel() = container.historyViewModel
    fun getMainViewModel() = container.mainViewModel
    fun getProfileViewModel() = container.profileViewModel

    fun setUserState(userRepository: UserRepository) {
        this.container.userRepository = userRepository
    }
    fun setPocketState(pocketRepository: PocketRepositoryImpl) {
        this.container.pocketRepository = pocketRepository
    }
}