package com.training.goldmarket.presentation

import android.content.Context
import com.google.gson.Gson
import com.training.goldmarket.repository.PocketRepositoryImpl
import com.training.goldmarket.repository.UserRepository

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

    fun clearData() {
        val userPreferences = mainActivity.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = userPreferences.edit()
        editor.clear().remove(USER_KEY)
        editor.clear().remove(POCKET_KEY)
        editor.commit()
    }

    fun setUserRepository(userRepository: UserRepository) {
        this.userRepository.setDataState(userRepository)
    }

    fun setPocketRepository(pocketRepository: PocketRepositoryImpl) {
        this.pocketRepository.setDataState(pocketRepository)
    }

}