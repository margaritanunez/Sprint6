package com.example.sprint6.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneRetrofitClient {
    companion object{
        private const val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getRetrofitPhone() : PhoneApi{
            val mRetrofit = Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(PhoneApi::class.java)
        }
    }
}