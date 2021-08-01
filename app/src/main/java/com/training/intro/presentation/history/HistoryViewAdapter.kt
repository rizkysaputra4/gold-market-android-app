package com.training.intro.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.intro.databinding.TransactionHistoryItemBinding
import com.training.intro.model.Transaction
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat

class HistoryViewAdapter(private val viewModel: HistoryViewModel):
    RecyclerView.Adapter<HistoryViewAdapter.HistoryViewHolder>() {

        class HistoryViewHolder(val binding: TransactionHistoryItemBinding):
                RecyclerView.ViewHolder(binding.root)

    var transactionHistories: List<Transaction> = viewModel.getAllTransaction()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = TransactionHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formatter: NumberFormat = DecimalFormat("'Rp.' #,###',00'")
        with(holder) {
            with(transactionHistories[position]) {
                binding.textViewTransactionName.text = transactionHistories[position].type.toString() + " | " +
                        transactionHistories[position].pocketName + " | " +
                        transactionHistories[position].productType
                binding.textViewTransactionDate.text = parser.format(transactionHistories[position].date)
                binding.textViewQtyAndPrice.text = transactionHistories[position].qty.toString() +
                        " grams * " + formatter.format(transactionHistories[position].productPrice)
                binding.textViewTotalPrice.text = formatter.format((transactionHistories[position].qty * transactionHistories[position].productPrice))
            }
        }
    }

    override fun getItemCount(): Int {
        return transactionHistories.size
    }
}