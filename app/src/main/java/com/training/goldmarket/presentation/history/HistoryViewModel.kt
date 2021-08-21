package com.training.goldmarket.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.repository.TransactionRepository
import com.training.goldmarket.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(val transactionRepositoryImpl: TransactionRepository,
                       val userRepositoryImpl: UserRepository
                       ): ViewModel() {

    private var _transactionData = MutableLiveData<List<Transaction>>()
    val transactionLiveData: LiveData<List<Transaction>>
        get() {return _transactionData}

    fun loadTransactionData() {
        viewModelScope.launch(Dispatchers.IO) {
            _transactionData.postValue(
                userRepositoryImpl.currentUser?.let { transactionRepositoryImpl.getUserTransaction(it.userId) }
            )
        }
    }
}