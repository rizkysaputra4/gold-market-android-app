package com.training.intro.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.intro.databinding.PocketListItemViewBinding
import com.training.intro.model.Pocket

class PocketNavigationAdapter(private val delegate: PocketNavigationInterface,
                              private val viewModel: HomeViewModel):
    RecyclerView.Adapter<PocketNavigationAdapter.PocketViewHolder>() {

    val pocket = viewModel.getAllPocket()

    class PocketViewHolder(val binding: PocketListItemViewBinding):
        RecyclerView.ViewHolder(binding.root)

    interface PocketNavigationInterface {
        fun onClickPocketItem(pocket: Pocket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PocketViewHolder {
        val binding = PocketListItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PocketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PocketViewHolder, position: Int) {
        with(holder) {
            with(pocket[position]) {
                binding.pocketListItem.text = pocket[position].name
                binding.btnPocketItem.setOnClickListener {
                    delegate.onClickPocketItem(pocket[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return pocket.size
    }
}