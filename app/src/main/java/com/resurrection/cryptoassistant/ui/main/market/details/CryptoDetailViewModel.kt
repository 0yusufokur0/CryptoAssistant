package com.resurrection.cryptoassistant.ui.main.market.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.remote.RetrofitClient
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
    var disposable: CompositeDisposable? = null
    val dao = CryptoDatabase(getApplication()).cryptoDao()

    fun getCryptoById(id:String) {
        disposable = RetrofitClient(RetrofitClient.api.getCryptoByID(id), this::setVal).returnCompose()

    }

    private fun setVal(any: Any?) {
        cryptoDetail.value = any as CryptoDetailItem
    }

    fun insertFavorite(cryptoDetailItem: CryptoDetailItem) = viewModelScope.launch{
            dao.insertFavoriteCrypto(cryptoDetailItem)
    }





    override fun onCleared() {
        super.onCleared()
        disposable!!.clear()
    }


}
