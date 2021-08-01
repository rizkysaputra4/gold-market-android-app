package com.training.intro.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.intro.R
import com.training.intro.databinding.FragmentHomeBinding
import com.training.intro.model.Pocket
import com.training.intro.presentation.MainActivity
import com.training.intro.utils.EventResult
import kotlinx.android.synthetic.main.buy_pocket_product.view.*
import kotlinx.android.synthetic.main.fragment_create_pocket.*
import kotlinx.android.synthetic.main.fragment_create_pocket.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_pocket_navigator.view.*
import kotlinx.android.synthetic.main.sell_pocket_product.*
import kotlinx.android.synthetic.main.sell_pocket_product.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class HomeFragment : Fragment(), PocketNavigationAdapter.PocketNavigationInterface {

    val pocketType = arrayListOf("Platinum", "Gold", "Silver")
    lateinit var selectedPocketType: String
    lateinit var viewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    lateinit var currentPocket: Pocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = (activity as MainActivity).getCreatePocketViewModel()
        viewModel.setPocketDataById(1)
        this.subscriber()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).showBottomNav()
        (activity as MainActivity)?.supportActionBar?.show()
        registerBtnListener(view)
    }

    fun registerBtnListener(view: View) {
        btnCreatePocket.setOnClickListener { showMessageBox() }
        cardViewPocketInfo.setOnClickListener { this.showPocketModalNavigator() }
        btnSellProduct.setOnClickListener { this.sellPocketProduct() }
        btnBuyProduct.setOnClickListener { this.buyPocketProduct() }
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

    fun showMessageBox(){
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
            (activity as MainActivity).saveState()
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
        }
    }

    fun sellPocketProduct(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.sell_pocket_product, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.setOnClickListener{ messageBoxInstance.dismiss() }
        messageBoxView.btnSellPocketProduct.setOnClickListener {
            viewModel.sellPocket(messageBoxView.inputNumberSell.text.toString().toDouble())
            messageBoxInstance.dismiss()
            (activity as MainActivity).saveState()
        }
    }

    fun buyPocketProduct(){
        val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.buy_pocket_product, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.setOnClickListener{ messageBoxInstance.dismiss() }
        messageBoxView.btnBuyPocketProduct.setOnClickListener {
            viewModel.buyPocket(messageBoxView.inputNumberBuy.text.toString().toDouble())
            messageBoxInstance.dismiss()
            (activity as MainActivity).saveState()
        }
    }

    fun subscriber() {
        binding.apply {
            var pocketObserver = Observer<EventResult> { event ->
                    when (event) {
                        is EventResult.Success -> {
                            this@HomeFragment.currentPocket = event.data as Pocket
                            this@HomeFragment.setView()
                        }
                        else -> EventResult.Idle
                    }
            }
            viewModel.pocketLiveData.observe(viewLifecycleOwner, pocketObserver)
        }
    }

    override fun onClickPocketItem(pocket: Pocket) {
        viewModel.setPocketData(pocket)
    }

    fun setView() {
        val formatter: NumberFormat = DecimalFormat("'Rp.' #,###',00'")
        val priceSell: Double = currentPocket.product.priceSell
        val totalPrice: Double = currentPocket.qty
        binding.apply {
            viewPocketTitle.text = "Pocket " + currentPocket.name.uppercase()
            viewPocketTotalPrice.text = formatter.format((priceSell*totalPrice))
            viewPocketGram.text = (currentPocket.qty).toString() + " gram " + currentPocket.product.type.toString().lowercase()
            viewTotalPocket.text = "Total pocket kamu " + viewModel.getAllPocket().size.toString()
            btnBuyProduct.text = "buy " + currentPocket.product.type.toString()
            btnSellProduct.text = "sell " + currentPocket.product.type.toString()
            viewTextBuyProduct.text = formatter.format(currentPocket.product.priceBuy)
            viewTextSellProduct.text = formatter.format(currentPocket.product.priceSell)
        }
    }

}