package com.training.goldmarket.data.repository

import android.util.Log
import com.training.goldmarket.data.db.UserDao
import com.training.goldmarket.data.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao): UserRepository {

    override var currentUser: User? = null

    override suspend fun checkIfUserNameAndPasswordMatch(user: User): User? {
        var users = userDao.getAllUser()
        return users.stream().filter { userData ->
            if (user.userName == userData.userName && user.password == userData.password) {
                this.currentUser = userData
                return@filter true
            }
            return@filter false
        }
        .findFirst().orElse(null)
    }

    override fun insertNewUser(userName: String, email: String, password: String) {
        userDao.insert(User(userName = userName, email = email, password = password))
    }

    override fun editUserData(user: User) {
        userDao.update(user)
        Log.d("USERS", userDao.getAllUser().toString())
    }

    override fun getUserById(id: String): User {
        return userDao.getUserByUserId(id)
    }
}