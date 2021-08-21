package com.training.goldmarket.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.goldmarket.databinding.FragmentHistoryBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : DaggerFragment() {

    lateinit var historyAdapter: HistoryViewAdapter

    @Inject
    lateinit var viewModel: HistoryViewModel
    lateinit var binding: FragmentHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Transaction"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        historyAdapter = HistoryViewAdapter()
        this.subscriber()
        viewModel.loadTransactionData()
        this.registerView()
        return binding.root
    }

    fun registerView() {
        binding.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryFragment.context)
            adapter = historyAdapter
        }
    }

    fun subscriber() {
        viewModel.transactionLiveData.observe(viewLifecycleOwner, {
            it?.apply {
                (recyclerViewHistory.adapter as HistoryViewAdapter).transactionHistories = this
                recyclerViewHistory.adapter?.notifyDataSetChanged()
            }
        })
    }
}