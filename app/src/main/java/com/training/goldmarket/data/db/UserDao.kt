package com.training.goldmarket.data.db

import androidx.room.Dao
import androidx.room.Query
import com.training.goldmarket.data.entity.User
import com.training.goldmarket.utils.AppConstant

@Dao
interface UserDao: BaseDao<User> {

    @Query("SELECT * FROM ${AppConstant.USER_TABLE}")
    fun getAllUser(): List<User>

}