package com.training.intro

import android.app.Application
import com.training.intro.di.DependencyContainer

class BaseApplication: Application() {

    private val container: DependencyContainer by lazy {
        DependencyContainer()
    }

    fun getLoginViewModel() = container.loginViewModel
    fun getregisterViewModel() = container.registerViewModel
    fun getCreatePocketViewModel() = container.createPocketViewMode
    fun getHistoryViewModel() = container.historyViewModel
    fun getMainViewModel() = container.mainViewModel
}