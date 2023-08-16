package com.example.sprint6.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {
    @GET("products/")
    suspend fun getData() : Response<List<Phone>>

    @GET("details/{id}")
    suspend fun getDetailPhone(@Path("id")id:Int): Response<List<PhoneDetail>>
}