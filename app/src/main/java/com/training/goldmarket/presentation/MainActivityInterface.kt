package com.training.goldmarket.presentation

import com.training.goldmarket.presentation.history.HistoryViewModel
import com.training.goldmarket.presentation.home.HomeViewModel
import com.training.goldmarket.presentation.login.LoginViewModel
import com.training.goldmarket.presentation.profile.ProfileViewModel
import com.training.goldmarket.presentation.register.RegisterViewModel
import com.training.goldmarket.presentation.welcome.WelcomeViewModel

interface MainActivityInterface {

    fun getRegisterViewModel(): RegisterViewModel
    fun getLoginViewModel(): LoginViewModel
    fun getCreatePocketViewModel(): HomeViewModel
    fun getHistoryViewModel(): HistoryViewModel
    fun getProfileViewModel(): ProfileViewModel
    fun getWelcomeViewModel(): WelcomeViewModel
}