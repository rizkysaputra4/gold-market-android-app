package com.training.intro.presentation.login

import com.training.intro.model.User
import com.training.intro.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) {

    fun login(userName: String, password: String): Boolean {
        val isAuthorized = repository.checkIfUserNameAndPasswordMatch(User(0 , userName,"", password))
        return isAuthorized
    }

}