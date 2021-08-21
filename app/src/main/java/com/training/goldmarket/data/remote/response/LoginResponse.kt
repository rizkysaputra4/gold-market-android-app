package com.training.goldmarket.data.remote.response

data class LoginResponse (
    val token:  String,
    val type:  String,
    val id:  String,
    val userName:  String,
    val email:  String,
    val firstName:  String,
    val lastName:  String,
    val address:  String,
    val status: Integer,
    val dateOfBirth: String,
    val roles: List<String>
)
