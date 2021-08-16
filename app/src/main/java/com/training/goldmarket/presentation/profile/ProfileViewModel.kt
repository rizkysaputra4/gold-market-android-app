package com.training.goldmarket.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.entity.User
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository,
                        private val sharedPref: SharedPreference
                       ): ViewModel() {

    lateinit var view: ProfileFragment
    var _user = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() {return _user}

    fun editUser(id: String,userName: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val editedUser = User(userId = id, userName = userName, email = email, password = password)
            userRepository.editUserData(editedUser)
            _user.postValue(editedUser)
        }
    }

    fun getUser(): User {
        _user.value = userRepository.currentUser?: User("1", "boymen", "boy@mail.com", "boymen")
        return userRepository.currentUser?: User("1", "boymen", "boy@mail.com", "boymen")
    }

    fun onClickEditProfile() {
        view.modalEditProfile()
    }

    fun onClickLogout() {
        sharedPref.clearData(AppConstant.CURRENT_USER)
        userRepository.currentUser = null
        view.view?.let { view.logout(it) }
    }
}