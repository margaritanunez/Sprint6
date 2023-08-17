package com.example.sprint6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sprint6.data.remote.PhoneDetail

@Dao
interface PhoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneEntity: List<PhoneEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailPhones (detailPhoneEntity: DetailPhoneEntity)

    @Query("select * from tabla_telefonos order by id ASC")
    fun getPhones(): LiveData<List<PhoneEntity>>

    @Query ("SELECT * FROM tabla_detalle_telefonos WHERE id = :id ")
    fun getPhoneDetail(id:Int): LiveData<DetailPhoneEntity>
    // para obtener un elemento dado su id. detalle
}
