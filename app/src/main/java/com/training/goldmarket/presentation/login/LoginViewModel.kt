package com.training.goldmarket.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.entity.User
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.data.repository.UserRepositoryImpl
import com.training.goldmarket.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repositoryImpl: UserRepository,
                                         private val sharedPref: SharedPreference
                     ): ViewModel() {

    lateinit var view: LoginFragment
    var userName: String = ""
    var password: String = ""

    var _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean>
        get() = _isAuthorized

    fun onClickLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = async { repositoryImpl.checkIfUserNameAndPasswordMatch(User("0" , userName,"", password)) }
            if (result.await() != null) {
                _isAuthorized.postValue(true)
                repositoryImpl.currentUser?.let { sharedPref.save(AppConstant.CURRENT_USER, it.userId) }
            } else {
                _isAuthorized.postValue(false)
            }
       }
    }

    fun onClickToRegister() {
        view.navigateToRegister()
    }
}