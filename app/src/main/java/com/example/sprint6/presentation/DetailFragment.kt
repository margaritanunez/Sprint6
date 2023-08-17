package com.example.sprint6.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.sprint6.R
import com.example.sprint6.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "id"

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        phoneViewModel.detailLiveData(param1).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.imagenPhoneDetalle.load(it.imagen)
                binding.tvNombreDetalle.text = it.nombre
                binding.tvPrecioAhora.text = it.precio.toString()
                binding.tvPrecioAntes.text = it.precioAntes.toString()
                binding.tvDescripcionDetalle.text = it.descripcion
                if (!it.credito) {
                    binding.tvCredito.text = "Solo pago en efectivo"
                } else {
                    binding.tvCredito.text = "Acepta tarjeta de crédito"
                }
            }

        }
        return binding.root

    }
}