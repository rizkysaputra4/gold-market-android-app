package com.training.goldmarket.presentation.profile

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import com.training.goldmarket.R
import com.training.goldmarket.databinding.FragmentProfileBinding
import com.training.goldmarket.data.entity.User
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.presentation.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.modal_edit_user_data.view.*
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    @Inject
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

//        this.viewModel = (activity as MainActivity).getProfileViewModel()
        this.viewModel.view = this
        this.subscriber()
        this.user = viewModel.getUser()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setView()
    }

    private fun setView() {
        binding.apply {
            Log.d("INITPROFILEVIE1", user.toString())
            textViewUserName.text = user.userName
            textViewEmail.text = user.email
            Picasso.with(this@ProfileFragment.context)
                .load("https://accounts-cdn.9gag.com/media/avatar/27135224_100_21.jpg")
                .into(imageViewProfile)
        }
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
//        (activity as MainActivity).clearDataState()
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
                Log.d("INITPROFILEVIE2", user.toString())
                viewModel.editUser(
                    user.userId,
                    editTextUserName.text.toString(),
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
                messageBoxInstance.hide()
//                (activity as MainActivity).saveState()
            }
        }
    }
}