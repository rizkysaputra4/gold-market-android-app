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
import com.training.goldmarket.data.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(val pocketRepository: PocketRepository,
                    val transactionRepositoryImpl: TransactionRepository,
                    val userRepositoryImpl: UserRepository
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
            val APOCKET = pocketRepository.insertNewPocket(name, pocketType)
            setPocketData(APOCKET)
            Log.d("POCKET", APOCKET.toString())
            loadAllPocket()
        }
    }

    fun setPocketData(pocket: Pocket) {
        _pocketData.postValue(pocket)
    }

    fun loadAllPocket() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("USERREPO", userRepositoryImpl.hashCode().toString())
            _allPocket.postValue(userRepositoryImpl.currentUser?.let {
                pocketRepository.getAllPocketByUserId(it.userId)
            })
            try {
                _pocketData.postValue(_allPocket.value?.get(0))
            } catch (e: Exception) {
                Log.e("POCKET DATA", "Index out of bound")
            }
            Log.d("POCKET DATA", _pocketData.value.toString())
            Log.d("USER", userRepositoryImpl.currentUser.toString())
        }
    }

    fun sellPocket(gram: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            var pocket = _pocketData.value as Pocket
            pocket.qty = pocket.qty - gram
            pocketRepository.updatePocket(pocket)
            _pocketData.postValue(pocket)

            transactionRepositoryImpl.addTransaction(
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

            transactionRepositoryImpl.addTransaction(
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

    fun onClickBuyPocketProduct() {
        if (_pocketData.value != null) {
            view.buyPocketProduct()
        } else {
            view.showErrorToast("Create Pocket First")
        }
    }

    fun onClickSellPocketProduct() {
        if (_pocketData.value != null) {
            view.sellPocketProduct()
        } else {
            view.showErrorToast("Create Pocket First")
        }
    }

    fun onClickCreatePocket() { view.createPocketBox() }

    fun onClickPocketNavigate() { view.showPocketModalNavigator() }

}
