package com.training.goldmarket.presentation.welcome

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.goldmarket.R
import com.training.goldmarket.presentation.MainActivity

class SplashFragment : Fragment() {

    lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as MainActivity).getWelcomeViewModel()

        return inflater.inflate(R.layout.activity_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.navigation()
        super.onViewCreated(view, savedInstanceState)
    }

    fun navigation() {
        Handler(Looper.getMainLooper()).postDelayed({

            if (viewModel.checkIfUserExist()) findNavController().navigate(R.id.action_splashScreen_to_homeFragment)
            else findNavController().navigate(R.id.action_splashScreen_to_welcomePage)

        }, 2500)
    }

}