package com.example.sprint6.data

import com.example.sprint6.data.local.DetailPhoneEntity
import com.example.sprint6.data.local.PhoneEntity
import com.example.sprint6.data.remote.Phone
import com.example.sprint6.data.remote.PhoneDetail

fun Phone.transformar(): PhoneEntity =
    PhoneEntity(this.id, this.nombre, this.precio, this.imagen)

fun PhoneDetail.transformarDetalle(): DetailPhoneEntity =
    DetailPhoneEntity(this.id, this.nombre, this.precio, this.imagen, this.descripcion, this.precioAntes, this.credito)