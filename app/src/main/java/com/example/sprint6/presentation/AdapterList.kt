package com.example.sprint6.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sprint6.R
import com.example.sprint6.data.local.PhoneEntity
import com.example.sprint6.databinding.ItemListBinding

class AdapterList: RecyclerView.Adapter<AdapterList.ItemListViewHolder>() {
    private lateinit var binding: ItemListBinding
    private var listaTelefonos = mutableListOf<PhoneEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ItemListViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterList.ItemListViewHolder, position: Int) {
        val telefono = listaTelefonos[position]
        holder.bind(telefono)
    }

    override fun getItemCount(): Int {
        return listaTelefonos.size
    }

    fun setData(telefonos: List<PhoneEntity>){
        this.listaTelefonos.clear()
        this.listaTelefonos.addAll(telefonos)
        notifyDataSetChanged()
    }

    class ItemListViewHolder (val phoneBinding: ItemListBinding): RecyclerView.ViewHolder(phoneBinding.root){
        fun bind(smartphone: PhoneEntity){
            phoneBinding.tvName.text = smartphone.nombre
            phoneBinding.tvPrice.text = smartphone.precio.toString()
            phoneBinding.imgPhone.load(smartphone.imagen)

            phoneBinding.cvPhone.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", smartphone.id.toString())
                Navigation.findNavController(phoneBinding.root).navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }

        }

    }
}