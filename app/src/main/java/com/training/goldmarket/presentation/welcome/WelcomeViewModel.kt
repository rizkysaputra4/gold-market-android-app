package com.training.goldmarket.presentation.welcome

import androidx.lifecycle.ViewModel
import com.training.goldmarket.repository.UserRepository

class WelcomeViewModel(private val repository: UserRepository): ViewModel() {

    fun checkIfUserExist(): Boolean {
        return repository.currentUser != null
    }

}