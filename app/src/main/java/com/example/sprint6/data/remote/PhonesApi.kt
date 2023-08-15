package com.example.sprint6.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface PhonesApi {
    @GET("products/")
    suspend fun getData() : Response<Phones>
}