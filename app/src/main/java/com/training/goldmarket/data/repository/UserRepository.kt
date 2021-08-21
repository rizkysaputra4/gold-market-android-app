package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.User

interface UserRepository {

    var currentUser: User?

    suspend fun checkIfUserNameAndPasswordMatch(user: User): User?
    fun insertNewUser(userName: String, email: String, password: String)
    fun editUserData(user: User)
    fun getUserById(id: String): User
}