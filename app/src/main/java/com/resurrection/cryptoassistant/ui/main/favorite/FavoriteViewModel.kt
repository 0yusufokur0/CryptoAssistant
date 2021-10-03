package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.RepositoryTest
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application):BaseViewModel(application) {

    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
    val dao = CryptoDatabase(getApplication()).cryptoDao()
    var allFavoriteCrypto=  MutableLiveData<List<CryptoMarketModel>>()

    fun getCryptoDetailById(id:String)= viewModelScope.launch {
        cryptoDetail.value = RepositoryTest.api.getCryptoById(id)
    }

    fun getAllFavoriteCrypto() = viewModelScope.launch{
        allFavoriteCrypto!!.value = dao.getCryptoFavorite()
    }

    override fun onCleared() {
        super.onCleared()
    }


}