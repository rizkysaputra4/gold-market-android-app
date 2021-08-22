package com.training.goldmarket.presentation.profile

import android.os.Handler
import android.os.Looper
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
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val userRepositoryImpl: UserRepository,
                       private val sharedPref: SharedPreference
                       ): ViewModel() {

    lateinit var view: ProfileFragment
    var _user = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() {return _user}

    fun editUser(id: String,userName: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val editedUser = User(userId = id, userName = userName, email = email, password = password)
            if (userRepositoryImpl.editUserData(editedUser)) {
                Handler(Looper.getMainLooper()).post {
                    view.showToast("User Updated")
                }
                _user.postValue(editedUser)
            } else {
                Handler(Looper.getMainLooper()).post {
                    view.showToast("ERROR: Failed to edit user")
                }
            }
        }
    }

    fun getUser(): User {
        _user.value = userRepositoryImpl.currentUser?: User("1", "boymen", "boy@mail.com", "boymen")
        return userRepositoryImpl.currentUser?: User("1", "boymen", "boy@mail.com", "boymen")
    }

    fun onClickEditProfile() {
        view.modalEditProfile()
    }

    fun onClickLogout() {
        sharedPref.clearData(AppConstant.CURRENT_USER)
        userRepositoryImpl.currentUser = null
        view.view?.let { view.logout(it) }
    }
}