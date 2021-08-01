package com.training.intro.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.training.intro.R
import com.training.intro.databinding.ActivityMainBinding
import com.training.intro.presentation.history.HistoryViewModel
import com.training.intro.presentation.home.HomeFragment
import com.training.intro.presentation.home.HomeViewModel
import com.training.intro.presentation.login.LoginViewModel
import com.training.intro.presentation.register.RegisterViewModel
import com.training.intro.utils.baseApp

class MainActivity : AppCompatActivity(), MainActivityInterface {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.switchFragment(HomeFragment())
        this.setBtnListener()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.viewFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        viewModel = baseApp.getMainViewModel()
        this.hideBottomNav()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun setBtnListener() {

    }

    fun switchFragment(fragment: Fragment) {

    }

    fun getMainViewModel(): MainViewModel { return this.viewModel}

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
}