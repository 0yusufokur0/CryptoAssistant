package com.resurrection.cryptoassistant.ui.main.favorite.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.model.CryptoChartModel
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
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
class CryptoChartViewModel @Inject constructor(private val cryptoRepository: TestRepository) :
    BaseViewModel() {

    var cryptoChart = MutableLiveData<Resource<CryptoChartModel>>()

    fun getCryptoChartById(id: String) = viewModelScope.launch {
        cryptoRepository.getCryptoChartByID(id)
            .onStart {

            }.catch {

            }.collect {
                cryptoChart.postValue(it)
            }
    }

}