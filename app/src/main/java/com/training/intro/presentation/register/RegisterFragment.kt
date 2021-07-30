package com.training.intro.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.training.intro.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterFragment : Fragment() {
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

        this.registerOnClickBtn(view)
    }

    fun registerOnClickBtn(view: View) {
        btnToLoginFromRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_register_to_login)
        }
        btnRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_register_to_homeFragment)
        }
    }
}