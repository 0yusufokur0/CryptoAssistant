package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.repository.TestRepository
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import com.resurrection.cryptoassistant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val cryptoRepository: TestRepository) :
    BaseViewModel() {

    var cryptoDetail = MutableLiveData<Resource<CryptoDetailItem>>()

    var allFavoriteCrypto = MutableLiveData<Resource<List<CryptoMarketModel>>>()

    fun getCryptoDetailById(id: String) = viewModelScope.launch{
        cryptoRepository.getCryptoDetailById(id)
            .onStart {

            }.catch {

            }.collect {
                cryptoDetail.postValue(it)
            }
    }

    fun getAllFavoriteCrypto() = viewModelScope.launch {
        cryptoRepository.getCryptoFavorite()
            .onStart {

            }.catch {

            }.collect {
                allFavoriteCrypto.value = it
            }

    }

}