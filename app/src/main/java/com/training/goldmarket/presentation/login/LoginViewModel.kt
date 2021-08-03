package com.training.goldmarket.presentation.login

import com.training.goldmarket.model.User
import com.training.goldmarket.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) {

    lateinit var view: LoginFragment
    var userName: String = ""
    var password: String = ""

    fun onClickLogin() {
        val isAuthorized = repository.checkIfUserNameAndPasswordMatch(User(0 , userName,"", password))
        if (isAuthorized) { view.navigateToHome(); return }
        view.showErrorToast()

    }

    fun onClickToRegister() {
        view.navigateToRegister()
    }
}