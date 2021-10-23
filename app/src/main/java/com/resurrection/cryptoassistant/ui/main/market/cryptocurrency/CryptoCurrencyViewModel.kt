package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.data.repository.TestRepository
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import com.resurrection.cryptoassistant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCurrencyViewModel @Inject constructor(val cryptoRepository: TestRepository ) :
    BaseViewModel() {
    var allCrypto = MutableLiveData<Resource<List<CryptoMarketModel>>>()

    fun getAllCrypto() = viewModelScope.launch {
            cryptoRepository.getAllCrypto().onStart {

            }.catch {

            }.collect {
                allCrypto.postValue(it)
            }
    }
}
