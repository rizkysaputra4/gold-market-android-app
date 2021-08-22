package com.training.goldmarket.data.repository

import android.util.Log
import com.training.goldmarket.data.db.UserDao
import com.training.goldmarket.data.entity.User
import com.training.goldmarket.data.remote.api.UserApi
import com.training.goldmarket.data.remote.request.LoginRequest
import com.training.goldmarket.data.remote.request.RegisterRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi
): UserRepository {

    override var currentUser: User? = null

    override suspend fun checkIfUserNameAndPasswordMatch(user: User): User? {
        var request = userApi.login(LoginRequest(
            userName = user.userName,
            userPassword = user.password?: ""
        ))
        val remoteUser = request.body()
        if (request.isSuccessful && remoteUser != null) {
            currentUser = User(
                userId = remoteUser.id,
                userName = remoteUser.userName,
                email = remoteUser.email
            )
            return currentUser
        }
        return null
    }

    override suspend fun insertNewUser(userName: String, email: String, password: String): Boolean {
        userDao.insert(User(userName = userName, email = email, password = password))
        var response = userApi.register(
            RegisterRequest(
                userName = userName, userPassword = password, email = email, firstName = userName
            )
        )
        if (response.isSuccessful) { return true }
        return false
    }

    override suspend fun editUserData(user: User): Boolean {
        userDao.update(user)
        Log.d("USERS", userDao.getAllUser().toString())
        return true
    }

    override suspend fun getUserById(id: String): User? {
        val request = userApi.getCustomerById(id)
        val remoteUser = request.body()
        if (request.isSuccessful && remoteUser != null) {
            return User(
                userId = id,
                userName = remoteUser.userName,
                email = remoteUser.email
            )
        }
        return null
    }
}