package com.training.goldmarket.presentation.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.goldmarket.databinding.FragmentHistoryBinding
import com.training.goldmarket.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    lateinit var historyAdapter: HistoryViewAdapter
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
        viewModel = (activity as MainActivity).getHistoryViewModel()
        historyAdapter = HistoryViewAdapter(viewModel)
        this.registerView()
        return binding.root
    }

    fun registerView() {
        binding.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryFragment.context)
            adapter = historyAdapter
        }
    }

}