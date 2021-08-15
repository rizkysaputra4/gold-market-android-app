package com.training.goldmarket.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.entity.User
import com.training.goldmarket.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    lateinit var view: LoginFragment
    var userName: String = ""
    var password: String = ""

    var _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean>
        get() = _isAuthorized

    fun onClickLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = async { repository.checkIfUserNameAndPasswordMatch(User("0" , userName,"", password)) }
            _isAuthorized.postValue(result.await() != null)
            if (_isAuthorized.value == true) {
                repository.currentUser = result.await()
            }
       }
    }

    fun onClickToRegister() {
        view.navigateToRegister()
    }
}