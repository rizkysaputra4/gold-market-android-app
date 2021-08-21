package com.training.goldmarket.presentation

import androidx.lifecycle.ViewModel
import com.training.goldmarket.data.repository.PocketRepository
import com.training.goldmarket.data.repository.UserRepository
import com.training.goldmarket.data.repository.UserRepositoryImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepositoryImpl: UserRepository,
                                        private val pocketRepository: PocketRepository
                    ): ViewModel() {

    lateinit var mainActivity: MainActivity
}