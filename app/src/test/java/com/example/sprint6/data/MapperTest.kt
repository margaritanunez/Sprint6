package com.example.sprint6.data

import com.example.sprint6.data.remote.Phone
import com.example.sprint6.data.remote.PhoneDetail
import org.junit.Assert.*

import org.junit.Test

class MapperTest {

    @Test
    fun transformar() {
        // given
        val phone = Phone(1, "telefono", 12732, "com.example.cl")

        //when
        val result = phone.transformar()

        //then
        assertEquals(phone.id, result.id)
        assertEquals(phone.nombre, result.nombre)
        assertEquals(phone.precio, result.precio)
        assertEquals(phone.imagen, result.imagen)

    }

    @Test
    fun transformarDetalle() {

        //given
        val phoneDetail = PhoneDetail(1, "ajsaks", 182622, "com.example.cl", "alsashggsgajsj",182812, true )

        //when
        val resultDetail= phoneDetail.transformarDetalle()

        //then

        assertEquals(phoneDetail.id, resultDetail.id)
        assertEquals(phoneDetail.nombre, phoneDetail.nombre)
        assertEquals(phoneDetail.precio, resultDetail.precio)
        assertEquals(phoneDetail.imagen, resultDetail.imagen)
        assertEquals(phoneDetail.descripcion, resultDetail.descripcion)
        assertEquals(phoneDetail.precioAntes, resultDetail.precioAntes)
        assertEquals(phoneDetail.credito, resultDetail.credito)
    }
}