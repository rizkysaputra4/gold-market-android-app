package com.training.goldmarket.data.repository

import android.util.Log
import com.training.goldmarket.data.db.PocketDao
import com.training.goldmarket.data.entity.*
import com.training.goldmarket.data.preference.SharedPreference
import com.training.goldmarket.data.remote.api.PocketApi
import com.training.goldmarket.data.remote.request.Customer
import com.training.goldmarket.data.remote.request.EditPocketRequest
import com.training.goldmarket.data.remote.request.NewPocketRequest
import java.util.*
import javax.inject.Inject

class PocketRepositoryImpl @Inject constructor(
    private val pocketDao: PocketDao,
    private val userRepositoryImpl: UserRepository,
    private val pocketApi: PocketApi
): PocketRepository {
    var products = arrayListOf<Product>(
        Product(3, PocketType.Gold, 880000.0, 860000.0),
        Product(4, PocketType.Platinum, 980000.0, 960000.0),
        Product(5, PocketType.Silver, 680000.0, 660000.0)
    )
    var pockets = arrayListOf<Pocket>()

    init {
        var date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, -7)
    }

    override suspend fun getAllPocketByUserId(userId: String): List<Pocket> {
        val response = pocketApi.getCustomerPocket(userId, "0")
        if (response.isSuccessful) {
            var pockets = arrayListOf<Pocket>()
            response.body()?.forEach { pocket ->
                val newPocket = Pocket(
                    pocketId = pocket.id,
                    name = pocket.pocketName,
                    pocketOwnerId = userId,
                    product = products[0],
                    qty = pocket.pocketQty
                )
                products.stream().filter { p ->
                    if (p.productId == pocket.productId) {
                        newPocket.product = p
                    }
                    p.productId == pocket.productId }
                    .findFirst()
                pockets.add(newPocket)
            }
            return pockets
        }
        return arrayListOf()
    }

    override suspend fun updatePocket(pocket: Pocket): Boolean {
//        pocketDao.update(pocket)
        val editPocket = EditPocketRequest(
            id = pocket.pocketId?: "",
            pocketQty = pocket.qty.toInt(),
            pocketName = pocket.name,
            customer = Customer(pocket.pocketOwnerId?: ""),
            product = com.training.goldmarket.data.remote.request.Product(pocket.product.productId)
        )
        val response = pocketApi.updatePocket(
            editPocket
        )
        Log.d("EDITPOCKET", editPocket.toString())
        return response.isSuccessful
    }

    override suspend fun insertNewPocket(name: String, type: PocketType): Pocket? {
        var newPocket = Pocket( name = name, product = products[0],
            qty =  0.0, pocketOwnerId = userRepositoryImpl.currentUser?.userId)
        products.stream().filter { p ->
            if (p.type == type) {
                newPocket.product = p
                pockets.add(newPocket)
            }
            p.type == type }
            .findFirst()
//        pocketDao.insert(newPocket)
        Log.d("NEWPOCKET", newPocket.toString())
        if (newPocket.pocketOwnerId != null) {
            val pocketRequest = NewPocketRequest(
                pocketQty = newPocket.qty.toInt(),
                pocketName = newPocket.name,
                customer = Customer(newPocket.pocketOwnerId!!),
                product = com.training.goldmarket.data.remote.request.Product(newPocket.product.productId)
            )
            val request = pocketApi.registerNewPocket(pocketRequest)

            Log.d("NEWPOCKET", pocketRequest.toString())
            if (request.isSuccessful) {
                newPocket.pocketId = request.body()?.id ?: ""
                return newPocket
            }
            Log.d("NEWPOCKET", "Return isNOTSuccess ${request.code()}")
        }
        Log.d("NEWPOCKET", "Return null")
        return null
    }
}