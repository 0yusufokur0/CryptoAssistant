package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
    private var _cryptoDetail = MutableLiveData<Resource<CryptoDetailItem>>()
    private var _allFavoriteCrypto = MutableLiveData<Resource<List<CryptoMarketModel>>>()

    var cryptoDetail :LiveData<Resource<CryptoDetailItem>> = _cryptoDetail
    var allFavoriteCrypto : LiveData<Resource<List<CryptoMarketModel>>> = _allFavoriteCrypto

    fun getCryptoDetailById(id: String) = viewModelScope.launch{
        cryptoRepository.getCryptoDetailById(id)
            .onStart {
                // Loading Animation
            }.catch {
                // show fail load
            }.collect {
                _cryptoDetail.postValue(it)
            }
    }

    fun getAllFavoriteCrypto() = viewModelScope.launch {
        cryptoRepository.getCryptoFavorite()
            .onStart {
                // Loading Animation
            }.catch {
                // show fail load
            }.collect {
                _allFavoriteCrypto.value = it
            }

    }

}