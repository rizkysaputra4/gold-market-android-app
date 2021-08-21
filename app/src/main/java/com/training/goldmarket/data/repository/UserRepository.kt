package com.training.goldmarket.data.repository

import com.training.goldmarket.data.entity.User

interface UserRepository {

    var currentUser: User?

    suspend fun checkIfUserNameAndPasswordMatch(user: User): User?
    suspend fun insertNewUser(userName: String, email: String, password: String): Boolean
    suspend fun editUserData(user: User): Boolean
    suspend fun getUserById(id: String): User?
}