package com.example.sprint6.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
            param1 = it.getInt("id")?: 0
            Log.d("detail", param1.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        phoneViewModel.getDetailPhoneViewModel(param1)
        Log.d("detail", "param1=$param1")
        phoneViewModel.detailLiveData(param1).observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("detail", it.toString())
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
        binding.btnCorreo.setOnClickListener {
            val destinatario= "info@navaera.cl"
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            val textoEmail = "Hola: \n Vi este teléfono y me gustaría que me contactaran a este  \n correo o al siguiente número:  \n Quedo atento."
            binding.mensajetxt.setText(textoEmail)


            emailIntent.type = "text/plain"
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, destinatario)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo")
            emailIntent.putExtra(Intent.EXTRA_TEXT, binding.mensajetxt.text)

            startActivity(Intent.createChooser(emailIntent, "Consultar por producto..."))
        }

        return binding.root

    }
}