package com.training.goldmarket.data.remote.api

import com.training.goldmarket.data.entity.Transaction
import com.training.goldmarket.data.remote.response.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionApi {

    @POST("purchase")
    suspend fun postTransaction(@Body transaction: Transaction): Response<Transaction>

    @GET("purchase/{id}")
    suspend fun getTransactionHistory(@Path("id") id: String): Response<List<Transaction>>
}