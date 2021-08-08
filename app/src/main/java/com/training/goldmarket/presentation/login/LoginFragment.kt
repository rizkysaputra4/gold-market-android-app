package com.training.goldmarket.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.training.goldmarket.R
import com.training.goldmarket.databinding.FragmentLoginBinding
import com.training.goldmarket.presentation.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).getLoginViewModel()
        viewModel.view = this
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = (activity as MainActivity).getLoginViewModel()
        }
        this.hideBottomNav()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun navigateToRegister() {
        this.view?.let { Navigation.findNavController(it).navigate(R.id.action_login_to_register) }
    }

    fun navigateToHome() {
        this.view?.let { Navigation.findNavController(it).navigate(R.id.action_login_to_homeFragment) }
        (activity as MainActivity).saveState()
    }

    fun showErrorToast() {
        Toast.makeText(activity, "Username or password not match", Toast.LENGTH_LONG).show()
    }

    fun hideBottomNav() {
        (activity as MainActivity).supportActionBar?.hide()
        (activity as MainActivity).bottomNavigationView.visibility = View.GONE
    }
}