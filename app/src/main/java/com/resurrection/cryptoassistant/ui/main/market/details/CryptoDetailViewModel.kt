package com.resurrection.cryptoassistant.ui.main.market.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.remote.RetrofitClient
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CryptoDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    var crypto = MutableLiveData<CryptoDetailItem>()
    var disposable: CompositeDisposable? = null
    fun getCryptoById(id:String) {
        disposable = RetrofitClient(RetrofitClient.api.getCryptoByID(id), this::setVal).returnCompose()

    }

    private fun setVal(any: Any?) {
        crypto.value = any as CryptoDetailItem
    }

    override fun onCleared() {
        super.onCleared()
        disposable!!.clear()
    }


}
