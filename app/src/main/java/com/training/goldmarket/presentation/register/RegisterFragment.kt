package com.training.goldmarket.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.training.goldmarket.R
import com.training.goldmarket.databinding.FragmentRegisterBinding
import com.training.goldmarket.presentation.MainActivity

class RegisterFragment : Fragment() {

    lateinit var viewModel: RegisterViewModel
    lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = (activity as MainActivity).getRegisterViewModel()
        viewModel.view = this
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewModel = (activity as MainActivity).getRegisterViewModel()
    }

    fun navigateToLogin() {
        this.view?.let { Navigation.findNavController(it).navigate(R.id.action_register_to_login) }
        (activity as MainActivity).saveState()
    }
}