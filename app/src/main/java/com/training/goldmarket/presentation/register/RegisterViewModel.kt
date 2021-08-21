package com.training.goldmarket.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.data.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val repositoryImpl: UserRepository): ViewModel() {

    lateinit var view: RegisterFragment

    var userName: String = ""
    var email: String = ""
    var password: String = ""

    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.insertNewUser(userName, email, password)
        }
        view.navigateToLogin()
    }

    fun navigateToLogin() {
        view.navigateToLogin()
    }
}