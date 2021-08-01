package com.training.intro.utils

import androidx.appcompat.app.AppCompatActivity
import com.training.intro.BaseApplication

val AppCompatActivity.baseApp: BaseApplication
    get() = (application as BaseApplication)