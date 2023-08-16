package com.example.sprint6.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint6.data.Repository
import com.example.sprint6.data.local.PhonesDataBase
import com.example.sprint6.data.remote.PhoneRetrofitClient
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    fun phoneLiveData() = repository.getPhones()

    init {
        val api = PhoneRetrofitClient.getRetrofitPhone()
        val phonesDataBase = PhonesDataBase.getDatabase(application).getPhoneDao()
        repository = Repository(api, phonesDataBase)
    }

    fun getAllPhones() = viewModelScope.launch {
        repository.chargePhones()
    }

    fun getDetailPhoneViewModel (id:Int) = viewModelScope.launch {
        repository.chargeDetailPhones(id)
    }

    fun detailLiveData(id:Int) =repository.getPhoneDetail(id)

}