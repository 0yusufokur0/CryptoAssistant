package com.resurrection.cryptoassistant.ui.main.favorite.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.model.CryptoChartModel
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
    private var _cryptoChart = MutableLiveData<Resource<CryptoChartModel>>()
    val cryptoChart :LiveData<Resource<CryptoChartModel>> = _cryptoChart

    fun getCryptoChartById(id: String) = viewModelScope.launch {
        cryptoRepository.getCryptoChartByID(id)
            .onStart {
                // Loading Animation
            }.catch {
                // show 'not load graph on graphview'
            }.collect {
                _cryptoChart.postValue(it)
            }
    }
}