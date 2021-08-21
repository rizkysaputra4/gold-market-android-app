package com.training.goldmarket.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.training.goldmarket.R
import com.training.goldmarket.databinding.FragmentLoginBinding
import com.training.goldmarket.presentation.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: LoginViewModel
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.view = this
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        this.subscriber()
        this.hideBottomNav()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LOGIN", "CREATED " + viewModel.isAuthorized.value.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel._isAuthorized.postValue(false)
    }

    fun navigateToRegister() {
        findNavController().navigate(R.id.action_login_to_register)
    }

    fun navigateToHome() {
        findNavController().navigate(R.id.action_login_to_homeFragment)
    }

    fun showErrorToast() {
        Toast.makeText(activity, "Username or password not match", Toast.LENGTH_LONG).show()
    }

    fun hideBottomNav() {
        (activity as MainActivity).supportActionBar?.hide()
        (activity as MainActivity).bottomNavigationView.visibility = View.GONE
    }

    fun subscriber() {
        var isAuthorizedObserver = Observer<Boolean> { it ->
            if (it) {
                this.navigateToHome()
            }
            else { this.showErrorToast() }
        }
        viewModel.isAuthorized.observe(viewLifecycleOwner, isAuthorizedObserver)
    }
}
