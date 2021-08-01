package com.training.intro.presentation.register

import com.training.intro.model.User
import com.training.intro.repository.UserRepository

class RegisterViewModel(private val repository: UserRepository) {

    fun register(userName: String, email: String, pasword: String) {
        repository.insertNewUser(userName, email, pasword)
    }
}