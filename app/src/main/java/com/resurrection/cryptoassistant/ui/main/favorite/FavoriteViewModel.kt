package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application):BaseViewModel(application) {
    val dao = CryptoDatabase(getApplication()).cryptoDao()
    var cryptoDetails = MutableLiveData<List<CryptoDetailItem>>()

    fun getFavorite() = viewModelScope.launch{
        cryptoDetails.value = dao.getCryptoFavoriteByParameter()
    }
}