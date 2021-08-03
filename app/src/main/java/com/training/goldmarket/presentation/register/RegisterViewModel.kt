package com.training.goldmarket.presentation.register

import com.training.goldmarket.repository.UserRepository

class RegisterViewModel(private val repository: UserRepository) {

    lateinit var view: RegisterFragment

    var userName: String = ""
    var email: String = ""
    var password: String = ""

    fun register() {
        repository.insertNewUser(userName, email, password)
        view.navigateToLogin()
    }

    fun navigateToLogin() {
        view.navigateToLogin()
    }
}