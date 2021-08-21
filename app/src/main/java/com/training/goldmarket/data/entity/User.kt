package com.training.goldmarket.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(

    @PrimaryKey
    var userId: String = UUID.randomUUID().toString(),
    val userName: String,
    val email: String?,
    val password: String
) {
}