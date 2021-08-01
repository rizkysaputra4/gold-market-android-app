package com.training.intro.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.training.intro.R
import com.training.intro.di.DependencyContainer
import com.training.intro.model.User
import com.training.intro.presentation.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterFragment : Fragment() {

    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewModel = (activity as MainActivity).getRegisterViewModel()
        this.registerOnClickBtn(view)
    }

    fun registerOnClickBtn(view: View) {
        btnToLoginFromRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_register_to_login)
        }
        btnRegister.setOnClickListener {
            onClickRegister(view, textInputUsername.text.toString(),
                textinputEmail.text.toString(), textInputPassword.text.toString())
        }
    }

    fun onClickRegister(view: View, userName: String, email: String, password: String) {
        viewModel.register(userName, email, password)
        Navigation.findNavController(view).navigate(R.id.action_register_to_login)
        (activity as MainActivity).saveState()
    }
}