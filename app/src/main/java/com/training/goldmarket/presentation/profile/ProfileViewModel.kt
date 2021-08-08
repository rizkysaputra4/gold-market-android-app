package com.training.goldmarket.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.training.goldmarket.model.User
import com.training.goldmarket.repository.UserRepository

class ProfileViewModel(private val userRepository: UserRepository) {

    lateinit var view: ProfileFragment
    var _user = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() {return _user}

    fun editUser(id: Int,userName: String, email: String, password: String) {
        val editedUser = User(id, userName, email, password)
        userRepository.editUserData(editedUser)
        this._user.value = editedUser
    }

    fun getUser(): User {
        return userRepository.currentUser?: User(1, "boymen", "boy@mail.com", "boymen")
    }

    fun onClickEditProfile() {
        view.modalEditProfile()
    }

    fun onClickLogout() {
        view.view?.let { view.logout(it) }
    }
}