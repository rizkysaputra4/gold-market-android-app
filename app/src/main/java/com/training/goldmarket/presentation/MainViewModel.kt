package com.training.goldmarket.presentation

import android.content.Context
import com.google.gson.Gson
import com.training.goldmarket.data.repository.PocketRepository
import com.training.goldmarket.data.repository.PocketRepositoryImpl
import com.training.goldmarket.data.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository,
                    private val pocketRepository: PocketRepository
                    ) {

    lateinit var mainActivity: MainActivity

}