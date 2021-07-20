package com.training.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.registerOnClickBtn()
    }

    fun registerOnClickBtn() {
        btnToLoginFromRegister.setOnClickListener {
            Intent(this, Login::class.java).also {
                startActivity(it)
            }
        }
    }
}