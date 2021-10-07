package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : BaseViewModel(application) {
    var app = application
    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
/*
    val dao = CryptoDatabase(getApplication()).cryptoDao()
*/
    var allFavoriteCrypto = MutableLiveData<List<CryptoMarketModel>>()

    fun getCryptoDetailById(id: String) = viewModelScope.launch {
        cryptoDetail.value = CryptoRepository(app).api.getCryptoById(id)
    }

    fun getAllFavoriteCrypto() = viewModelScope.launch {
        /*allFavoriteCrypto.value = dao.getCryptoFavorite()*/
        allFavoriteCrypto.value = CryptoRepository(app).dao.getCryptoFavorite()
        CryptoRepository(app).api
    }




}