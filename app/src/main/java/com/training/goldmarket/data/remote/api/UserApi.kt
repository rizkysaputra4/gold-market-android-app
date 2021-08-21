package com.training.goldmarket.data.remote.api

import com.training.goldmarket.data.remote.request.LoginRequest
import com.training.goldmarket.data.remote.request.RegisterRequest
import com.training.goldmarket.data.remote.response.LoginResponse
import com.training.goldmarket.data.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("auth/signup")
    suspend fun register(@Body user: RegisterRequest): Response<RegisterResponse>

    @POST("auth/signin")
    suspend fun login(@Body user: LoginRequest): Response<LoginResponse>

    @GET("customers/{id}")
    suspend fun getCustomerById(@Path("id") id: String): Response<RegisterRequest>
}