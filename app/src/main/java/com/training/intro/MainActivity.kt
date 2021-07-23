package com.training.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.training.intro.fragment.HistoryFragment
import com.training.intro.fragment.HomeFragment
import com.training.intro.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.switchFragment(HomeFragment())

        this.setBtnListener()
    }

    fun setBtnListener() {
        btnHome.setOnClickListener { this.switchFragment(HomeFragment()) }
        btnHistory.setOnClickListener{ this.switchFragment(HistoryFragment())}
        btnProfile.setOnClickListener{ this.switchFragment(ProfileFragment())}
    }

    fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        when(fragment) {
            is HomeFragment -> {
                transaction.replace(R.id.viewFragment, fragment).commit()
            }
            is HistoryFragment -> {
                transaction.replace(R.id.viewFragment, fragment)
                transaction.commit()
            }
            is ProfileFragment -> {
                transaction.replace(R.id.viewFragment, fragment)
                transaction.commit()
            }
        }
    }
}