package com.resurrection.cryptoassistant.ui.main.market.details

import android.app.Application
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject
import com.google.firebase.firestore.DocumentSnapshot

import com.google.firebase.firestore.QuerySnapshot

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.ktx.database
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel


@HiltViewModel
class CryptoDetailViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {
    var job : Job? = null
    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
    val dao = CryptoDatabase(getApplication()).cryptoDao()
    var isFavorite = MutableLiveData<Boolean?>()
    var app = application


    fun getCryptoDetailById(id:String){
        job = CoroutineScope(Dispatchers.IO).launch {
            var temp = RepositoryTest(app).api.getCryptoById(id)
            cryptoDetail.postValue(temp)
        }
    }

    fun insertFavoriteCrypto(cmm: CryptoMarketModel)= viewModelScope.launch{
        dao.insertFavoriteCrypto(cmm)
    }



    fun isFavorite(checkId: String) {
        /*
        * Böyle bir id li crypto varmı kontrol edilecek
        *
        * */

        val user = Firebase.auth.currentUser
        if (user != null) {
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(checkId).get().addOnSuccessListener {
                    if (it.value == null){
                        isFavorite.value = false
                        println(it)
                    }else{
                        isFavorite.value = true
                    }

                    println(it)
                }.addOnFailureListener {
                    isFavorite.value = false
                }

        } else { // No user is signed in }
    }



}
    override fun onCleared() {
        super.onCleared()
        job = null

    }
    }