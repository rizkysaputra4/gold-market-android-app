package com.training.intro.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.training.intro.R
import com.training.intro.databinding.ActivityMainBinding
import com.training.intro.presentation.history.HistoryViewModel
import com.training.intro.presentation.home.HomeFragment
import com.training.intro.presentation.home.HomeViewModel
import com.training.intro.presentation.login.LoginViewModel
import com.training.intro.presentation.profile.ProfileViewModel
import com.training.intro.presentation.register.RegisterViewModel
import com.training.intro.repository.PocketRepositoryImpl
import com.training.intro.repository.UserRepository
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
        viewModel.mainActivity = this
        this.hideBottomNav()
        this.setDataState()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun saveState() { viewModel.saveToLocal() }

    fun setDataState() {
        var gson = Gson()
        val userPreferences: SharedPreferences? = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        if (userPreferences != null) {
            val userJson = userPreferences.getString("user", "isEmpty")
            val pocketJson = userPreferences.getString("pocket", "isEmpty")
            if (userJson != null && !userJson.contains("isEmpty")) {
                val user: UserRepository = gson.fromJson(userJson, UserRepository::class.java)
                this.viewModel.setUserRepository(user)
            }
            if (pocketJson != null &&!pocketJson.contains("isEmpty")) {
                val pocket: PocketRepositoryImpl = gson.fromJson(pocketJson, PocketRepositoryImpl::class.java)
                this.viewModel.setPocketRepository(pocket)
            }
        }
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

    override fun getProfileViewModel(): ProfileViewModel {
        return baseApp.getProfileViewModel()
    }
}