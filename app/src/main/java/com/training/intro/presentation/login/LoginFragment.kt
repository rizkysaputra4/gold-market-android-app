package com.training.intro.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.training.intro.R
import com.training.intro.di.DependencyContainer
import com.training.intro.presentation.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

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
        viewModel = (activity as MainActivity).getLoginViewModel()
        this.registerBtnListener(view)
    }

    fun registerBtnListener(view: View) {
        btnToRegisterFromLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register)
        }
        btnLogin.setOnClickListener {
            loginHandler(view)
        }
    }

    fun loginHandler(view: View) {
        val isAuthorized = viewModel.login(viewTextUserName.text.toString(), viewTextPassword.text.toString())
        if (isAuthorized) {
            Navigation.findNavController(view).navigate(R.id.action_login_to_homeFragment)
            return
        }
        Toast.makeText(activity, "Username or password not match", Toast.LENGTH_LONG).show()
    }
}