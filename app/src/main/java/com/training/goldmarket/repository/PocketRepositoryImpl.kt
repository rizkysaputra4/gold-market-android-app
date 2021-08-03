package com.training.goldmarket.repository

import com.training.goldmarket.model.*
import java.util.*

class PocketRepositoryImpl: PocketRepository {
    var products = arrayListOf<Product>(
        Product(1, PocketType.Gold, 880000.0, 860000.0),
        Product(2, PocketType.Platinum, 980000.0, 960000.0),
        Product(3, PocketType.Silver, 680000.0, 660000.0))
    var pockets = arrayListOf(Pocket(1, "GoldPocket", products[0], 0.0))
    var transactions = arrayListOf<Transaction>()

    init {
        var date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, -7)
        transactions.add(Transaction(1, date.time, TransactionType.Buy, "GoldPocket",
            870000.0, PocketType.Gold, 1.0))
    }

    override fun getPocketById(id: Int): Pocket {
        return pockets.stream().filter{it.id == id}
            .findFirst()
            .orElse(Pocket(0, "", products[0], 0.0))
    }

    override fun getAllPocket(): List<Pocket> {
        return pockets
    }

    override fun updatePocket(pocket: Pocket) {

        pockets.filterIndexed { i, p ->
            if (p.id == pocket.id) { pockets[i] = pocket }
            p.id == pocket.id
        }
            .firstOrNull()
    }

    override fun insertNewPocket(name: String, type: PocketType): Pocket {
        var newPocket = Pocket(pockets.size - 1, name, products[0], 0.0)
        products.stream().filter { p ->
            if (p.type == type) {
                newPocket = Pocket(pockets.size - 1, name, p, 0.0)
                pockets.add(newPocket)
            }
            p.type == type
        }
            .findFirst()
        return newPocket
    }

    override fun getAllTransaction(): List<Transaction> {
        return transactions
    }

    override fun addTransaction(transactionType: TransactionType, productPrice: Double, pocketName: String, pocketType: PocketType, qty: Double) {
        transactions.add(Transaction(transactions.size, Calendar.getInstance().time, transactionType,
            pocketName, productPrice, pocketType, qty))
    }

    fun setDataState(pocketRepositoryImpl: PocketRepositoryImpl) {
        this.products = pocketRepositoryImpl.products
        this.pockets = pocketRepositoryImpl.pockets
        this.transactions = pocketRepositoryImpl.transactions
    }

}