package com.training.goldmarket.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    lateinit var view: RegisterFragment

    var userName: String = ""
    var email: String = ""
    var password: String = ""

    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNewUser(userName, email, password)
        }
        view.navigateToLogin()
    }

    fun navigateToLogin() {
        view.navigateToLogin()
    }
}