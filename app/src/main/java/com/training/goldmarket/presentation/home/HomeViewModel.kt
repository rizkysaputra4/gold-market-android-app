package com.training.goldmarket.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.training.goldmarket.model.Pocket
import com.training.goldmarket.model.PocketType
import com.training.goldmarket.model.Product
import com.training.goldmarket.model.TransactionType
import com.training.goldmarket.repository.PocketRepository
import com.training.goldmarket.utils.EventResult

class HomeViewModel(val repository: PocketRepository):ViewModel() {

    private var _pocketData = MutableLiveData<EventResult>()
    val pocketLiveData: LiveData<EventResult>
        get() {return _pocketData}

    fun addNewPocket(name: String, type: String) {
        var pocketType: PocketType = PocketType.Gold
        when(type.lowercase().trim()) {
            "gold" -> pocketType = PocketType.Gold
            "silver" -> pocketType = PocketType.Silver
            "platinum" -> pocketType = PocketType.Platinum
        }

        _pocketData.value = EventResult.Success(repository.insertNewPocket(name, pocketType))
    }

    fun setPocketData(pocket: Pocket) {
        _pocketData.value = EventResult.Success(pocket)
    }

    fun setPocketDataById(id: Int) {
        _pocketData.value = EventResult.Success(repository.getPocketById(id))
    }

    fun getAllPocket(): List<Pocket> {
        return repository.getAllPocket()
    }

    fun sellPocket(gram: Double) {
        var pocket: Pocket? = null
        when(_pocketData.value) {
            is EventResult.Success -> {
                pocket = (_pocketData.value as EventResult.Success).data as Pocket
                pocket.qty = pocket.qty - gram
                repository.updatePocket(pocket)
                _pocketData.value = EventResult.Success(pocket)
                repository.addTransaction(TransactionType.Sell, pocket.product.priceSell,
                    pocket.name, pocket.product.type, gram)
            }
        }
    }

    fun buyPocket(gram: Double) {
        var pocket: Pocket? = null
        when(_pocketData.value) {
            is EventResult.Success -> {
                pocket = (_pocketData.value as EventResult.Success).data as Pocket
                pocket.qty = pocket.qty + gram
                repository.updatePocket(pocket)
                _pocketData.value = EventResult.Success(pocket)
                repository.addTransaction(TransactionType.Buy, pocket.product.priceBuy,
                    pocket.name, pocket.product.type, gram)
            }
        }
    }

    fun getPocketById(id: Int): Pocket {
        return repository.getPocketById(id)
    }
}
