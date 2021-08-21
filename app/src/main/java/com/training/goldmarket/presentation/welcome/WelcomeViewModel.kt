package com.training.goldmarket.presentation.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.data.repository.UserRepositoryImpl
import com.training.goldmarket.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(private val repositoryImpl: UserRepository,
                                           private val sharedPref: SharedPreference
                       ): ViewModel() {

    fun checkIfUserExist(): Boolean {
        val savedUser = sharedPref.retrieve(AppConstant.CURRENT_USER)
        if (savedUser != null) {
            viewModelScope.launch(Dispatchers.IO) {
                repositoryImpl.currentUser = repositoryImpl.getUserById(savedUser)
                Log.d("USERREPO", repositoryImpl.hashCode().toString())
            }
        }
        return savedUser != null
    }
}