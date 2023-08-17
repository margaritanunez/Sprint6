package com.example.sprint6.data

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
        val response = phoneApi.getData()
        if(response.isSuccessful){
            val resp = response.body()
            resp?.let{phones ->
                val phonesEntity = phones.map { it.transformar()}
                phoneDao.insertPhone(phonesEntity)
                
            }
        }

    }

    suspend fun chargeDetailPhones(id:Int){
        val responseDetail = phoneApi.getDetailPhone(id)
        if (responseDetail.isSuccessful){
            val respDetail = responseDetail.body()
            respDetail?.let { phonesDetail->
                val detailsPhoneEntity = phonesDetail.map { it.transformarDetalle()}
                phoneDao.insertDetailPhones(detailsPhoneEntity)
            }
        }

    }

    fun Phone.transformar(): PhoneEntity =
        PhoneEntity(this.id, this.nombre, this.precio, this.imagen)

    fun PhoneDetail.transformarDetalle(): DetailPhoneEntity =
        DetailPhoneEntity(this.id, this.nombre, this.precio, this.imagen, this.descripcion, this.precioAntes, this.credito)
}