package com.training.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_welcome_page.*

class WelcomePage : AppCompatActivity() {

    lateinit var textViewTitle: TextView
    val TAB = "main activity"
    var num: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        Log.d(TAB,"onCreate")

        this.setBtnListener()
    }

    fun setBtnListener() {
        btnToRegister.setOnClickListener {
            Intent(this, Register::class.java).also {
                startActivity(it)
            }
        }

        btnToLogin.setOnClickListener {
            Intent(this, Login::class.java).also {
                startActivity(it)
            }
        }
    }
}