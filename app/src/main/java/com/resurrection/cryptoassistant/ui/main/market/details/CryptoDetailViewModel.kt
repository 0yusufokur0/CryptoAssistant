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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {
    var job : Job? = null
    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
    val dao = CryptoDatabase(getApplication()).cryptoDao()

    fun getCryptoDetailById(id:String){
        job = CoroutineScope(Dispatchers.IO).launch {
            var temp = RepositoryTest.api.getCryptoById(id)
            cryptoDetail.postValue(temp)
        }
    }

    fun insertFavoriteCrypto(cmm: CryptoMarketModel)= viewModelScope.launch{
        dao.insertFavoriteCrypto(cmm)
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }


}
