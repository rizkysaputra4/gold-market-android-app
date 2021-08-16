package com.training.goldmarket.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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
import com.training.goldmarket.utils.baseApp

class MainActivity : AppCompatActivity(), MainActivityInterface {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseApp.initRepository(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.viewFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        viewModel = baseApp.getMainViewModel()
        viewModel.mainActivity = this
        this.hideBottomNav()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    override fun getRegisterViewModel(): RegisterViewModel {
        return baseApp.getregisterViewModel()
    }

    override fun getLoginViewModel(): LoginViewModel {
        return baseApp.getLoginViewModel()
    }

    override fun getCreatePocketViewModel(): HomeViewModel {
        return baseApp.getCreatePocketViewModel()
    }

    override fun getHistoryViewModel(): HistoryViewModel {
        return baseApp.getHistoryViewModel()
    }

    override fun getProfileViewModel(): ProfileViewModel {
        return baseApp.getProfileViewModel()
    }

    override fun getWelcomeViewModel(): WelcomeViewModel {
        return baseApp.getWelcomeViewModel()
    }
}