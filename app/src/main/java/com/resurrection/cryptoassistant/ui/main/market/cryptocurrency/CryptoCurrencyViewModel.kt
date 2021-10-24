package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.repository.TestRepository
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import com.resurrection.cryptoassistant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CryptoCurrencyViewModel @Inject constructor(val cryptoRepository: TestRepository) :
    BaseViewModel() {
    private var _allCrypto = MutableLiveData<Resource<List<CryptoMarketModel>>>()
    var allCrypto: LiveData<Resource<List<CryptoMarketModel>>> = _allCrypto

    fun getAllCrypto() {
        launchOnIO {
            cryptoRepository.getAllCrypto()
                .onStart {
                    // Loading Animation
                }.catch {
                    // show fail load
                }.collect {
                    _allCrypto.postValue(it)
                }
        }

    }
}
/*
cryptoRepository.getAllCrypto()
.onStart {
    // Loading Animation
}.catch {
    // show fail load
}.collect {
    _allCrypto.postValue(it)
}*/
