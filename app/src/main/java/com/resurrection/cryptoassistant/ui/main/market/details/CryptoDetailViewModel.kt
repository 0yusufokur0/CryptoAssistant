package com.resurrection.cryptoassistant.ui.main.market.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.RepositoryTest
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    var cryptoDetail = MutableLiveData<CryptoDetailItem>()

    val dao = CryptoDatabase(getApplication()).cryptoDao()

    fun getCryptoDetailById(id:String) = viewModelScope.launch{
        cryptoDetail.value = RepositoryTest.api.getCryptoById(id)
    }
    fun insertFavoriteCrypto(cmm: CryptoMarketModel)= viewModelScope.launch{
        dao.insertFavoriteCrypto(cmm)
    }

    override fun onCleared() {
        super.onCleared()
    }


}
