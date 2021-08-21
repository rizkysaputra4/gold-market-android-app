package com.training.goldmarket.data.remote.request

import java.util.*

data class RegisterRequest (
    var firstName: String,
    var lastName: String = firstName,
    var dateOfBirth: String? = null,
    var address: String = UUID.randomUUID().toString(),
    var status: Int? = null,
    var userName: String,
    var userPassword: String,
    var email: String,
    var role: Set<String> = setOf<String>("ROLE_USER"),
)

