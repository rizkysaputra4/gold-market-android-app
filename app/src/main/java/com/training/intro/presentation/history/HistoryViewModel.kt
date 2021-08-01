package com.training.intro.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.training.intro.model.Transaction
import com.training.intro.repository.PocketRepository

class HistoryViewModel(val repository: PocketRepository): ViewModel() {

    private var _transactionData = MutableLiveData<List<Transaction>>()
    val transactionLiveData: LiveData<List<Transaction>>
        get() {return _transactionData}

    init {
        _transactionData.value = this.getAllTransaction()
    }

    fun getAllTransaction(): List<Transaction> {
        return repository.getAllTransaction()
    }
}