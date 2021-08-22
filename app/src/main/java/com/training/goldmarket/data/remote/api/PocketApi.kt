package com.training.goldmarket.data.remote.api

import com.training.goldmarket.data.remote.request.EditPocketRequest
import com.training.goldmarket.data.remote.request.NewPocketRequest
import com.training.goldmarket.data.remote.response.GetUserResponse
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface PocketApi {

    @POST("pockets")
    suspend fun registerNewPocket(@Body pocket: NewPocketRequest): Response<GetUserResponse>

    @GET("/customers/{id}/pockets/{idProduct}")
    suspend fun getCustomerPocket(@Path("id") userId: String,
                                  @Path("idProduct") productId: String
                                  ): Response<List<GetUserResponse>>

    @PUT("pockets")
    suspend fun updatePocket(@Body pocket: EditPocketRequest): Response<GetUserResponse>

}