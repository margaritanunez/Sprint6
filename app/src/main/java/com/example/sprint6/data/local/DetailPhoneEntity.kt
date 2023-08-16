package com.example.sprint6.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_detalle_telefonos")
data class DetailPhoneEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: String,
    val descripcion: String,
    val precioAntes: Int,
    val credito: Boolean
)
