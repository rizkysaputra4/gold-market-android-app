package com.training.intro.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.training.intro.model.User
import com.training.intro.repository.PocketRepository
import com.training.intro.repository.PocketRepositoryImpl
import com.training.intro.repository.UserRepository
import com.training.intro.utils.baseApp

class MainViewModel(private val userRepository: UserRepository,
                    private val pocketRepository: PocketRepositoryImpl
                    ) {

    lateinit var mainActivity: MainActivity
    var gson = Gson()
    val USER_KEY = "user"
    val POCKET_KEY = "pocket"

    fun saveToLocal() {
        val userJson: String = gson.toJson(userRepository)
        val pocketJson: String = gson.toJson(pocketRepository)
        val userPreferences = mainActivity.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = userPreferences.edit()
        editor.putString(USER_KEY, userJson)
        editor.putString(POCKET_KEY, pocketJson)
        editor.apply()
    }

    fun setUserRepository(userRepository: UserRepository) {
        this.userRepository.setDataState(userRepository)
    }

    fun setPocketRepository(pocketRepository: PocketRepositoryImpl) {
        this.pocketRepository.setDataState(pocketRepository)
    }

}