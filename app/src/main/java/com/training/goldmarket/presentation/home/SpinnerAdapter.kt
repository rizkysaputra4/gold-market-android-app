//package com.training.intro.presentation.home.createpocket
//
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Spinner
//import android.widget.SpinnerAdapter
//import androidx.fragment.app.FragmentActivity
//import com.training.intro.R
//import com.training.intro.presentation.MainActivity
//
//class SpinnerAdapter(val fragment: CreatePocketFragment, val t: View, val activity: FragmentActivity): AdapterView.OnItemSelectedListener {
//
//    val pocketType = arrayListOf("Platinum", "Gold", "Silver")
//    val spinner = t.findViewById<Spinner>(R.id.spinner)
//    val adapter = ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, pocketType) as SpinnerAdapter
//
//    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        fragment.setPocketTypeSpinner(pocketType[p2])
//        println("AAAAAA")
//    }
//
//    override fun onNothingSelected(p0: AdapterView<*>?) {
//
//    }
//
//    interface spinnerDelegate {
//        fun setPocketTypeSpinner(data: String)
//    }
//}