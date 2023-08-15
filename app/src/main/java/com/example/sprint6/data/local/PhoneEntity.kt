package com.example.sprint6.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName= "tabla_telefonos")
data class PhoneEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: String
)
