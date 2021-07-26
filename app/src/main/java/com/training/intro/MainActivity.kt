package com.training.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.training.intro.databinding.ActivityMainBinding
import com.training.intro.fragment.HistoryFragment
import com.training.intro.fragment.HomeFragment
import com.training.intro.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.switchFragment(HomeFragment())

        this.setBtnListener()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.viewFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        this.hideBottomNav()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun setBtnListener() {
//        btnHome.setOnClickListener { this.switchFragment(HomeFragment()) }
//        btnHistory.setOnClickListener{ this.switchFragment(HistoryFragment())}
//        btnProfile.setOnClickListener{ this.switchFragment(ProfileFragment())}
    }

    fun switchFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        when(fragment) {
//            is HomeFragment -> {
//                transaction.replace(R.id.viewFragment, fragment).commit()
//            }
//            is HistoryFragment -> {
//                transaction.replace(R.id.viewFragment, fragment)
//                transaction.commit()
//            }
//            is ProfileFragment -> {
//                transaction.replace(R.id.viewFragment, fragment)
//                transaction.commit()
//            }
//        }
    }

    fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}