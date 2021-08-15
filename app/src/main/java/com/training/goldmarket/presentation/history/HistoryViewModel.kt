package com.training.goldmarket.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.repository.PocketRepository
import com.training.goldmarket.data.repository.TransactionRepository
import com.training.goldmarket.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(val transactionRepository: TransactionRepository,
                       val userRepository: UserRepository
                       ): ViewModel() {

    private var _transactionData = MutableLiveData<List<Transaction>>()
    val transactionLiveData: LiveData<List<Transaction>>
        get() {return _transactionData}

    fun loadTransactionData() {
        viewModelScope.launch(Dispatchers.IO) {
            _transactionData.postValue(
                userRepository.currentUser?.let { transactionRepository.getUserTransaction(it.userId) }
            )
        }
    }
}