package com.training.goldmarket.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.training.goldmarket.R
import com.training.goldmarket.databinding.ActivityMainBinding
import com.training.goldmarket.presentation.history.HistoryViewModel
import com.training.goldmarket.presentation.home.HomeFragment
import com.training.goldmarket.presentation.home.HomeViewModel
import com.training.goldmarket.presentation.login.LoginViewModel
import com.training.goldmarket.presentation.profile.ProfileViewModel
import com.training.goldmarket.presentation.register.RegisterViewModel
import com.training.goldmarket.presentation.welcome.WelcomeViewModel
import com.training.goldmarket.utils.ViewModelFactoryBase
import com.training.goldmarket.utils.baseApp
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainActivityInterface {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.initViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.viewFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)

        this.hideBottomNav()
    }

    private fun initViewModel() {
        this.viewModel = ViewModelProvider(this, ViewModelFactoryBase {
            viewModel
        }).get(MainViewModel::class.java)
    }

    fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}