package com.resurrection.cryptoassistant.ui.main.favorite.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.model.CryptoChartModel
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoChartViewModel  @Inject constructor(private val cryptoRepository: CryptoRepository) :
    BaseViewModel() {


    var job: Job? = null

    var cryptoChart = MutableLiveData<CryptoChartModel>()


    fun getCryptoChartById(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var temp = cryptoRepository.api.getCryptoChartByID(id)
            cryptoChart.postValue(temp)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }
    }