package com.training.intro.repository

import com.training.intro.model.User

class UserRepository {

    var users = arrayListOf(User(1, "boymen", "boy@mail.com", "boymen"))
    var currentUser: User = User(1, "boymen", "boy@mail.com", "boymen")

    fun checkIfUserNameAndPasswordMatch(user: User): Boolean {
        return users.stream().filter { userData ->
            if (user.userName == userData.userName && user.password == userData.password) {
                this.currentUser = userData
                return@filter true
            }
            return@filter false
        }
        .findFirst().orElse(null) != null
    }

    fun insertNewUser(userName: String, email: String, password: String) {
        users.add(User(users.size, userName, email, password))
    }

    fun editUserData(user: User) {
        users.filterIndexed { i, u ->
            if (u.id == user.id) {
                users[i] = user
                return@filterIndexed true
            }
            return@filterIndexed false
        }
            .firstOrNull()
    }

    fun setDataState(userRepository: UserRepository) {
        this.currentUser = userRepository.currentUser
        this.users = userRepository.users
    }

    fun getUserById(id: Int): User {
        return users.stream().filter { u -> u.id == id }.findFirst().orElse(null)
    }

}