package com.example.sprint6.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneRetrofitClient {
    companion object{
        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getRetrofitPhone() : PhoneApi{
            val mRetrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(PhoneApi::class.java)
        }
    }
}