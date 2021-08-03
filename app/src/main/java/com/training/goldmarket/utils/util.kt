package com.training.goldmarket.utils

import androidx.appcompat.app.AppCompatActivity
import com.training.goldmarket.BaseApplication

val AppCompatActivity.baseApp: BaseApplication
    get() = (application as BaseApplication)