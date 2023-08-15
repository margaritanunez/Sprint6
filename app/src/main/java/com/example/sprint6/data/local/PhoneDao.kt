package com.example.sprint6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneEntity: List<PhoneEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhones (phonesEntity: List<PhoneEntity>)

    @Query("select * from tabla_telefonos order by id ASC")
    fun getPhones(): LiveData<List<PhoneEntity>>

    @Query ("SELECT * FROM tabla_telefonos WHERE id = :id ")
    fun getTerreno(id:String): LiveData<PhoneEntity>
    // para obtener un elemento dado su id.
}