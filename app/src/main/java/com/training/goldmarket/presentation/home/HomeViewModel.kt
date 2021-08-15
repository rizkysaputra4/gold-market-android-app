package com.training.goldmarket.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.data.entity.PocketType
import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.entity.TransactionType
import com.training.goldmarket.data.repository.PocketRepository
import com.training.goldmarket.data.repository.TransactionRepository
import com.training.goldmarket.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(val pocketRepository: PocketRepository,
                    val transactionRepository: TransactionRepository,
                    val userRepository: UserRepository
                    ):ViewModel() {

    lateinit var view: HomeFragment
    private var _pocketData = MutableLiveData<Pocket>()
    val pocketLiveData: LiveData<Pocket>
        get() {return _pocketData}

    private var _allPocket = MutableLiveData<List<Pocket>>()
    val allPocket: LiveData<List<Pocket>>
        get() { return _allPocket }

    fun addNewPocket(name: String, type: String) {
        var pocketType: PocketType = PocketType.Gold
        when(type.lowercase().trim()) {
            "gold" -> pocketType = PocketType.Gold
            "silver" -> pocketType = PocketType.Silver
            "platinum" -> pocketType = PocketType.Platinum
        }

        viewModelScope.launch(Dispatchers.IO) {
            _pocketData.postValue(pocketRepository.insertNewPocket(name, pocketType))
        }
    }

    fun setPocketData(pocket: Pocket) {
        _pocketData.postValue(pocket)
    }

    fun loadAllPocket() {
        viewModelScope.launch(Dispatchers.IO) {
            _allPocket.postValue(userRepository.currentUser?.let {
                pocketRepository.getAllPocketByUserId(
                    it.userId)
            })
            try {
                _pocketData.postValue(_allPocket.value?.get(0))
            } catch (e: Exception) {
                Log.e("POCKET DATA", "Index out of bound")
            }
            Log.d("POCKET DATA", _pocketData.value.toString())
        }
    }

    fun sellPocket(gram: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            var pocket = _pocketData.value as Pocket
            pocket.qty = pocket.qty - gram
            pocketRepository.updatePocket(pocket)
            _pocketData.postValue(pocket)

            transactionRepository.addTransaction(
                Transaction(
                    date = Calendar.getInstance().time,
                    type = TransactionType.Sell,
                    pocketName = pocket.name,
                    productPrice = pocket.product.priceSell,
                    qty = gram,
                    transactionOwnerId = pocket.pocketOwnerId,
                    productType = pocket.product.type
                )
            )
        }
    }

    fun buyPocket(gram: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            var pocket = _pocketData.value as Pocket
            pocket.qty = pocket.qty + gram
            pocketRepository.updatePocket(pocket)
            _pocketData.postValue(pocket)

            transactionRepository.addTransaction(
                Transaction(
                    date = Calendar.getInstance().time,
                    type = TransactionType.Buy,
                    pocketName = pocket.name,
                    productPrice = pocket.product.priceBuy,
                    qty = gram,
                    transactionOwnerId = pocket.pocketOwnerId,
                    productType = pocket.product.type
                )
            )
        }
    }

    fun onClickBuyPocketProduct() { view.buyPocketProduct() }

    fun onClickSellPocketProduct() { view.sellPocketProduct() }

    fun onClickCreatePocket() { view.createPocketBox() }

    fun onClickPocketNavigate() { view.showPocketModalNavigator() }

}
