package com.example.sprint6.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sprint6.data.local.DetailPhoneEntity
import com.example.sprint6.data.local.PhoneDao
import com.example.sprint6.data.local.PhoneEntity
import com.example.sprint6.data.remote.Phone
import com.example.sprint6.data.remote.PhoneApi
import com.example.sprint6.data.remote.PhoneDetail

class Repository (private val phoneApi: PhoneApi, private val phoneDao: PhoneDao){

    fun getPhones() : LiveData<List<PhoneEntity>> = phoneDao.getPhones()
    fun getPhoneDetail(id: Int): LiveData<DetailPhoneEntity> = phoneDao.getPhoneDetail(id)

    suspend fun chargePhones(){
        try{
            val response = phoneApi.getData()
            if(response.isSuccessful){
                val resp = response.body()
                resp?.let{phones ->
                    val phonesEntity = phones.map { it.transformar()}
                    phoneDao.insertPhone(phonesEntity)

                }
            }else{
                Log.e("repository", response.errorBody().toString())
            }

        } catch(exception: Exception) {
            Log.e("catch", "")
        }


    }

    suspend fun chargeDetailPhones(id:Int){
        Log.d("repository", id.toString())
        try {
            val responseDetail = phoneApi.getDetailPhone(id)
            Log.d("repository", responseDetail.isSuccessful.toString())
            if (responseDetail.isSuccessful){
                val respDetail = responseDetail.body()
                respDetail?.let { phonesDetail->
                    val detailsPhoneEntity = phonesDetail.transformarDetalle()
                    phoneDao.insertDetailPhones(detailsPhoneEntity)
                }
            }else{
                Log.e("repository", responseDetail.errorBody().toString())
            }
        }catch (exception: Exception){
            Log.e("catch", "")
        }
    }

}