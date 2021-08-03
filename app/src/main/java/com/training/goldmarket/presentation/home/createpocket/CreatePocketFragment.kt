package com.training.goldmarket.presentation.home.createpocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.training.goldmarket.R
import com.training.goldmarket.presentation.MainActivity
import com.training.goldmarket.presentation.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_create_pocket.*


class CreatePocketFragment : Fragment() {

    val pocketType = arrayListOf("Platinum", "Gold", "Silver")
    lateinit var selectedPocketType: String
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_pocket, container, false)
        spinnerRegister(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).getCreatePocketViewModel()
        registerButton()
    }

    fun registerButton() {
        btnAddPocket.setOnClickListener {
            viewModel.addNewPocket(textPocketName.text.toString(), this.selectedPocketType)
            activity?.onBackPressed()
            (activity as MainActivity).setDataState()
        }
    }

    fun spinnerRegister(view: View) {
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        spinner?.adapter = ArrayAdapter(requireActivity(), R.layout.support_simple_spinner_dropdown_item, pocketType) as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                selectedPocketType = type
                println(selectedPocketType)
            }
        }
    }
}