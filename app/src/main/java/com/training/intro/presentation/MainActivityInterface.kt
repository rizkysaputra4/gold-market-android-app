package com.training.intro.presentation

import com.training.intro.presentation.history.HistoryViewModel
import com.training.intro.presentation.home.HomeViewModel
import com.training.intro.presentation.login.LoginViewModel
import com.training.intro.presentation.profile.ProfileViewModel
import com.training.intro.presentation.register.RegisterViewModel

interface MainActivityInterface {

    fun getRegisterViewModel(): RegisterViewModel
    fun getLoginViewModel(): LoginViewModel
    fun getCreatePocketViewModel(): HomeViewModel
    fun getHistoryViewModel(): HistoryViewModel
    fun getProfileViewModel(): ProfileViewModel
}