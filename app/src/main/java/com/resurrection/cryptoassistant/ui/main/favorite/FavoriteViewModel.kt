package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.data.RepositoryTest
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : BaseViewModel(application) {
    var app = application
    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
/*
    val dao = CryptoDatabase(getApplication()).cryptoDao()
*/
    var allFavoriteCrypto = MutableLiveData<List<CryptoMarketModel>>()

    fun getCryptoDetailById(id: String) = viewModelScope.launch {
        cryptoDetail.value = RepositoryTest(app).api.getCryptoById(id)
    }

    fun getAllFavoriteCrypto() = viewModelScope.launch {
        /*allFavoriteCrypto.value = dao.getCryptoFavorite()*/
        allFavoriteCrypto.value = RepositoryTest(app).dao.getCryptoFavorite()
        RepositoryTest(app).api
    }




}