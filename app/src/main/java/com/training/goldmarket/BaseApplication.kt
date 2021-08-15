package com.training.goldmarket

import android.app.Application
import android.content.Context
import com.training.goldmarket.di.DependencyContainer

class BaseApplication: Application() {

    private val container: DependencyContainer by lazy {
        DependencyContainer()
    }

    fun initRepository(context: Context) {
        container.initRepository(context)
    }

    fun getLoginViewModel() = container.loginViewModel
    fun getregisterViewModel() = container.registerViewModel
    fun getCreatePocketViewModel() = container.createPocketViewMode
    fun getHistoryViewModel() = container.historyViewModel
    fun getMainViewModel() = container.mainViewModel
    fun getProfileViewModel() = container.profileViewModel
    fun getWelcomeViewModel() = container.welcomeViewModel
}