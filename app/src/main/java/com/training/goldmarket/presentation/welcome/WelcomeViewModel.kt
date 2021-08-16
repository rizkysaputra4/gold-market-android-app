package com.training.goldmarket.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: UserRepository,
                        private val sharedPref: SharedPreference
                       ): ViewModel() {

    fun checkIfUserExist(): Boolean {
        val savedUser = sharedPref.retrieve(AppConstant.CURRENT_USER)
        if (savedUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.currentUser = repository.getUserById(savedUser)
            }
        }
        return savedUser != null
    }
}