package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.RepositoryTest
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCurrencyViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    var allCrypto = MutableLiveData<List<CryptoMarketModel>>()

 fun getAllCrypto() =  viewModelScope.launch {
        allCrypto.value = RepositoryTest.api.getAllCrypto()
    }


    override fun onCleared() {
        super.onCleared()

    }


}
