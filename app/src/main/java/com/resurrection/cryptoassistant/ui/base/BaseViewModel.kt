package com.resurrection.cryptoassistant.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {
    private var job: Job? = null

    override val coroutineContext: CoroutineContext get() = Dispatchers.IO

    fun launchOnIO(function: suspend () -> Unit) {
        job = launch { function() }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
