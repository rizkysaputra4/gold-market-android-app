package com.training.intro.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.training.intro.model.User
import com.training.intro.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository) {

    var _user = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() {return _user}

    fun editUser(id: Int,userName: String, email: String, password: String) {
        val editedUser = User(id, userName, email, password)
        userRepository.editUserData(editedUser)
        this._user.value = editedUser
    }

    fun getUser(): User {
        return userRepository.currentUser
    }
}