package com.training.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.registerBtnListener()
    }

    fun registerBtnListener() {
        btnToRegisterFromLogin.setOnClickListener {
            Intent(this, Register::class.java).also {
                startActivity(it)
            }
        }
    }
}