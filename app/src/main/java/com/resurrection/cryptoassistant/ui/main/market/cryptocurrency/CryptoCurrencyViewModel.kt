package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCurrencyViewModel @Inject constructor(val cryptoRepository: CryptoRepository ) :
    BaseViewModel() {
    var job: Job? = null
    var allCrypto = MutableLiveData<List<CryptoMarketModel>>()

    fun getAllCrypto() = viewModelScope.launch {
        job = CoroutineScope(Dispatchers.IO).launch {
            var temp = cryptoRepository.api.getAllCrypto()
            allCrypto.postValue(temp)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }
}
