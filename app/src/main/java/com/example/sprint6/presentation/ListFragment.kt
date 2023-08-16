package com.example.sprint6.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sprint6.R
import com.example.sprint6.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        phoneViewModel.getAllPhones()
        return binding.root
    }

    private fun initAdapter() {
        val adapterList= AdapterList()
        binding.rvList.adapter = adapterList
        phoneViewModel.phoneLiveData().observe(viewLifecycleOwner){
            adapterList.setData(it)
        }
    }

}