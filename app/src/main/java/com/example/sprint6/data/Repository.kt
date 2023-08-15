package com.example.sprint6.data

import androidx.lifecycle.LiveData
import com.example.sprint6.data.local.PhoneDao
import com.example.sprint6.data.local.PhoneEntity
import com.example.sprint6.data.remote.Phone
import com.example.sprint6.data.remote.PhoneApi

class Repository (private val phoneApi: PhoneApi, private val phoneDao: PhoneDao){

    fun getPhones() : LiveData<List<PhoneEntity>> = phoneDao.getPhones()

    suspend fun chargePhones(){
        val response = phoneApi.getData()
        if(response.isSuccessful){
            val resp = response.body()
            resp?.let{phones ->
                val phonesEntity = phones.map { it.transformar()}
                phoneDao.insertPhone(phonesEntity)
                
            }
        }
        fun getPhone(id: String): LiveData<PhoneEntity> = phoneDao.getPhone(id)
        //devuelve un solo terreno. el nombre de la fx debe ser igual al que se encuentra en dao

    }

    fun Phone.transformar(): PhoneEntity =
        PhoneEntity(this.id, this.nombre, this.precio, this.imagen)




}