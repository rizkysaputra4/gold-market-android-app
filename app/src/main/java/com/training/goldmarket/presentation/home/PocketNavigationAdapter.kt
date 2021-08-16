package com.training.goldmarket.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.goldmarket.databinding.ModalPocketListItemViewBinding
import com.training.goldmarket.data.entity.Pocket

class PocketNavigationAdapter(private val delegate: PocketNavigationInterface,
                              private val viewModel: HomeViewModel):
    RecyclerView.Adapter<PocketNavigationAdapter.PocketViewHolder>() {

    var pocket: List<Pocket> = arrayListOf()

    class PocketViewHolder(val binding: ModalPocketListItemViewBinding):
        RecyclerView.ViewHolder(binding.root)

    interface PocketNavigationInterface {
        fun onClickPocketItem(pocket: Pocket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PocketViewHolder {
        val binding = ModalPocketListItemViewBinding.inflate(
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