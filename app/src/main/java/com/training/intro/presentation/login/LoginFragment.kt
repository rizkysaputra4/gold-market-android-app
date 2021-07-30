package com.training.intro.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.training.intro.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.registerBtnListener(view)
    }

    fun registerBtnListener(view: View) {
        btnToRegisterFromLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register)
        }
        btnLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_homeFragment)
        }
    }
}