package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) :
    BaseViewModel() {


    var job: Job? = null

    var cryptoDetail = MutableLiveData<CryptoDetailItem>()

    /*
        val dao = CryptoDatabase(getApplication()).cryptoDao()
    */
    var allFavoriteCrypto = MutableLiveData<List<CryptoMarketModel>>()

    fun getCryptoDetailById(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var temp = cryptoRepository.api.getCryptoById(id)
            cryptoDetail.postValue(temp)
        }
    }

    fun getAllFavoriteCrypto() = viewModelScope.launch {
        /*allFavoriteCrypto.value = dao.getCryptoFavorite()*/
        allFavoriteCrypto.value = cryptoRepository.dao.getCryptoFavorite()

    }


    override fun onCleared() {
        super.onCleared()
        job = null
    }
}