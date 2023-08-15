package com.example.sprint6.data.remote

import com.google.gson.annotations.SerializedName

/*{
    "id": 1,
    "name": "Samsung Galaxy A21s 64GB",
    "price": 167253,
    "image": "https://images.samsung.com/is/image/samsung/es-galaxy-a21s-sm-a217fzkoeub-262755098?$PD_GALLERY_L_JPG$"
}
*/

data class Phone(
    val id: Int,
    @SerializedName("name") val nombre: String,
    @SerializedName("price") val precio: Int,
    @SerializedName("image") val imagen: String
)
