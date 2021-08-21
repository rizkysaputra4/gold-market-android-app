package com.training.goldmarket.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.goldmarket.R
import com.training.goldmarket.databinding.FragmentHomeBinding
import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.presentation.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_create_pocket.*
import kotlinx.android.synthetic.main.fragment_create_pocket.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_pocket_navigator.view.*
import kotlinx.android.synthetic.main.modal_buy_pocket_product.view.*
import kotlinx.android.synthetic.main.modal_sell_pocket_product.view.*
import java.lang.Exception
import javax.inject.Inject

class HomeFragment : DaggerFragment(), PocketNavigationAdapter.PocketNavigationInterface {

    val pocketType = arrayListOf("Platinum", "Gold", "Silver")
    lateinit var selectedPocketType: String

    @Inject
    lateinit var viewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        viewModel.loadAllPocket()
        viewModel.view = this
        this.subscriber()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).showBottomNav()
        (activity as MainActivity)?.supportActionBar?.show()
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
            }
        }
    }

    fun createPocketBox(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.fragment_create_pocket, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val  messageBoxInstance = messageBoxBuilder.show()

        spinnerRegister(messageBoxView)
        messageBoxView.setOnClickListener(){
            messageBoxInstance.dismiss()
        }

        messageBoxView.btnAddPocket.setOnClickListener {
            viewModel.addNewPocket(messageBoxView.textPocketName.text.toString(), this.selectedPocketType)
            messageBoxInstance.dismiss()
        }
    }

    fun showPocketModalNavigator(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.fragment_pocket_navigator, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.setOnClickListener(){
            messageBoxInstance.dismiss()
        }
        messageBoxView.apply {
            recycleViewPocketNavigator.apply {
                layoutManager = LinearLayoutManager(messageBoxView.context)
                adapter = PocketNavigationAdapter(this@HomeFragment, viewModel)
            }
            viewModel.allPocket.observe(viewLifecycleOwner, {
                it?.let {
                    (recycleViewPocketNavigator.adapter as PocketNavigationAdapter).pocket = it
                    recycleViewPocketNavigator.adapter?.notifyDataSetChanged()
                }
            })
        }
    }

    fun sellPocketProduct(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.modal_sell_pocket_product, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.setOnClickListener{ messageBoxInstance.dismiss() }
        messageBoxView.btnSellPocketProduct.setOnClickListener {
            viewModel.sellPocket(messageBoxView.inputNumberSell.text.toString().toDouble())
            messageBoxInstance.dismiss()
        }
    }

    fun buyPocketProduct(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.modal_buy_pocket_product, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.setOnClickListener{ messageBoxInstance.dismiss() }
        messageBoxView.btnBuyPocketProduct.setOnClickListener {
            viewModel.buyPocket(messageBoxView.inputNumberBuy.text.toString().toDouble())
            messageBoxInstance.dismiss()
        }
    }

    fun subscriber() {
        viewModel.allPocket.observe(viewLifecycleOwner, {
            try {
                onClickPocketItem(it[0])
            } catch (e: Exception) {
                Log.d("POCKETS", "POCKET OUT OF BOUND")
            }
        })
    }

    override fun onClickPocketItem(pocket: Pocket) {
        viewModel.setPocketData(pocket)
    }

    fun showErrorToast(msg: String) {
        Toast.makeText(activity, "Error: ${msg}", Toast.LENGTH_LONG).show()
    }

}
