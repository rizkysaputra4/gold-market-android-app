package com.training.intro.presentation.profile

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.training.intro.R
import com.training.intro.databinding.FragmentProfileBinding
import com.training.intro.model.User
import com.training.intro.presentation.MainActivity
import com.training.intro.presentation.MainViewModel
import kotlinx.android.synthetic.main.buy_pocket_product.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.modal_edit_user_data.view.*


class ProfileFragment : Fragment() {

    lateinit var viewModel: ProfileViewModel
    lateinit var user: User
    lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Profile"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        this.viewModel = (activity as MainActivity).getProfileViewModel()
        this.subscriber()
        this.user = viewModel.getUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.registerBtnListener(view)
        this.setView()
    }

    private fun setView() {
        binding.apply {
            textViewUserName.text = user.userName
            textViewEmail.text = user.email
        }
    }

    fun registerBtnListener(view: View) {
        btnProfileDetail.setOnClickListener { println("Profile detail") }
        btnEditProfile.setOnClickListener { modalEditProfile() }
        btnLogout.setOnClickListener { logout(view) }
    }

    fun subscriber() {
        binding.apply {
            var userObserver = Observer<User> { u ->
                this@ProfileFragment.user = u
                this@ProfileFragment.setView()
            }
            viewModel.userLiveData.observe(viewLifecycleOwner, userObserver)
        }
    }

    fun logout(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_login)
    }

    fun modalEditProfile(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.modal_edit_user_data, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.setOnClickListener{ messageBoxInstance.dismiss() }
        messageBoxView.apply {
            editTextUserName.setText(user.userName)
            editTextEmail.setText(user.email)

            btnEditUserId.setOnClickListener {
                viewModel.editUser(
                    user.id,
                    editTextUserName.text.toString(),
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
                messageBoxInstance.hide()
                (activity as MainActivity).saveState()
            }
        }
    }
}