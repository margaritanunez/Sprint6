package com.example.sprint6.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sprint6.data.local.DetailPhoneEntity
import com.example.sprint6.databinding.ItemDetailsBinding

class AdapterDetail : RecyclerView.Adapter<AdapterDetail.ItemDetailViewHolder>() {
    lateinit var binding: ItemDetailsBinding
    private var listaDetalleTelefonos = mutableListOf<DetailPhoneEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailViewHolder {
        binding = ItemDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listaDetalleTelefonos.size
    }

    override fun onBindViewHolder(holder: ItemDetailViewHolder, position: Int) {
        val detalleTelefono = listaDetalleTelefonos[position]
        holder.bind(detalleTelefono)
    }

    fun setDataDetalle(detallesTelefonos: List<DetailPhoneEntity>){
        this.listaDetalleTelefonos.clear()
        this.listaDetalleTelefonos.addAll(detallesTelefonos)
        notifyDataSetChanged()
    }

    class ItemDetailViewHolder (val detalleTelefonoBinding: ItemDetailsBinding): RecyclerView.ViewHolder(detalleTelefonoBinding.root){
        fun bind(detalles: DetailPhoneEntity){
            detalleTelefonoBinding.imagenPhoneDetalle.load(detalles.imagen)
            detalleTelefonoBinding.tvNombreDetalle.text = detalles.nombre
            detalleTelefonoBinding.tvPrecioAhora.text = detalles.precio.toString()
            detalleTelefonoBinding.tvDescripciNDetalle.text = detalles.descripcion
            detalleTelefonoBinding.tvPrecioAntes.text = detalles.precioAntes.toString()
            detalleTelefonoBinding.tvCrDito.text = detalles.credito.toString()

        }

    }
}