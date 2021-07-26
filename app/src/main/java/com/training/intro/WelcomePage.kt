//package com.training.intro
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import kotlinx.android.synthetic.main.activity_welcome_page.*
//
//class WelcomePage : Fragment() {
//
//    lateinit var textViewTitle: TextView
//    val TAB = "main activity"
//    var num: Int = 0
//
////    private lateinit var binding:
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_welcome_page)
//        Log.d(TAB,"onCreate")
//
//        this.setBtnListener()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        (activity as MainActivity).showBottomNav()
//    }
//
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
////
////        return binding.root
////    }
//
//    fun setBtnListener() {
////        btnToRegister.setOnClickListener {
////            Intent(this, Register::class.java).also {
////                startActivity(it)
////            }
////        }
////
////        btnToLogin.setOnClickListener {
////            Intent(this, Login::class.java).also {
////                startActivity(it)
////            }
////        }
//    }
//}