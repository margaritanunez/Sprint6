package com.example.sprint6.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sprint6.R
import com.example.sprint6.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "id"

class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        initAdapterDetalle()
        phoneViewModel.getDetailPhoneViewModel(param1!!.toInt())
        return binding.root
    }

    private fun initAdapterDetalle() {
        val adapterDetail = AdapterDetail()
        binding.rvDetail.adapter= adapterDetail
        phoneViewModel.detailLiveData(param1!!.toInt()).observe(viewLifecycleOwner){
            adapterDetail.setDataDetalle(it)
        }
    }

}