package com.training.goldmarket.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import java.text.NumberFormat

object CurrencyFormat {

    val formatter: NumberFormat = DecimalFormat("'Rp.' #,###',00'")

    @BindingAdapter(value = ["currencyID"])
    @JvmStatic
    fun setCurrencyFormat(textView: TextView, currencyID: Double) {
        textView.setText(formatter.format(currencyID))
    }
}