package com.training.goldmarket.data.repository

import com.training.goldmarket.data.db.PocketDao
import com.training.goldmarket.data.entity.*
import java.util.*

class PocketRepositoryImpl(private val pocketDao: PocketDao, private val userRepository: UserRepository): PocketRepository {
    var products = arrayListOf<Product>(
        Product(1, PocketType.Gold, 880000.0, 860000.0),
        Product(2, PocketType.Platinum, 980000.0, 960000.0),
        Product(3, PocketType.Silver, 680000.0, 660000.0)
    )
    var pockets = arrayListOf<Pocket>()
    var transactions = arrayListOf<Transaction>()

    init {
        var date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, -7)
    }

    override fun getAllPocket(): List<Pocket> {
        return pocketDao.getAllPocket()
    }

    override fun getAllPocketByUserId(userId: String): List<Pocket> {
        return pocketDao.getAllPocketByUserId(userId)
    }

    override fun updatePocket(pocket: Pocket) {
        pocketDao.update(pocket)
    }

    override fun insertNewPocket(name: String, type: PocketType): Pocket {
        var newPocket = Pocket( name = name, product = products[0],
            qty =  0.0, pocketOwnerId = userRepository.currentUser?.userId)
        products.stream().filter { p ->
            if (p.type == type) {
                newPocket.product = p
                pockets.add(newPocket)
            }
            p.type == type }
            .findFirst()
        pocketDao.insert(newPocket)
        return newPocket
    }

    override fun getAllTransaction(): List<Transaction> {
        return transactions
    }
}