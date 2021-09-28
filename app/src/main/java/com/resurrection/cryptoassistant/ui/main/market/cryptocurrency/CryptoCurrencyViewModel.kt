package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.remote.RetrofitClient
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
@HiltViewModel
class CryptoCurrencyViewModel @Inject constructor(application: Application)  : BaseViewModel(application) {

     var allCrypto = MutableLiveData<List<CryptoMarketModel>>()
    var disposable:CompositeDisposable? = null
    fun getAllCrypto(){
        disposable = RetrofitClient(RetrofitClient.api.getAllCrypto(),this::setVal).returnCompose()

    }

    private fun setVal(any: Any?) {
        allCrypto.value = any as List<CryptoMarketModel>?
    }


    override fun onCleared() {
        super.onCleared()
        disposable!!.clear()
    }



}
