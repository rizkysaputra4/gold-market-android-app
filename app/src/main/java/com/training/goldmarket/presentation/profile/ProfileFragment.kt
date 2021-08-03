package com.training.goldmarket.presentation.profile

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.training.goldmarket.R
import com.training.goldmarket.databinding.FragmentProfileBinding
import com.training.goldmarket.model.User
import com.training.goldmarket.presentation.MainActivity
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
        (activity as MainActivity).clearDataState()
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