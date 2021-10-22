package com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail

import android.app.Application
import android.graphics.Color
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.data.repository.CryptoRepository
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteDetailViewModel @Inject constructor(val cryptoRepository: CryptoRepository) :
    BaseViewModel() {
    var job: Job? = null
    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
    var cryptoFromFirebase = MutableLiveData<FavouriteCryptoModel>()
    var isFavorite = MutableLiveData<Boolean?>()

    var isRemoved = MutableLiveData<Boolean>()

    fun getCryptoDetailById(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var temp = cryptoRepository.api.getCryptoById(id)
            cryptoDetail.postValue(temp)
        }
    }
    fun getCryptoByFirebase(id:String){
        val user = Firebase.auth.currentUser
        if (user != null) {
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(id).get()
                .addOnSuccessListener {
                    cryptoFromFirebase.value = it.getValue(FavouriteCryptoModel::class.java)

                }.addOnFailureListener { }
        } else { /*No user is signed in */
        }
    }

    fun insertFavoriteCrypto(cryptoDetail: CryptoDetailItem, favoriteImageView: ImageView) =
        viewModelScope.launch {
            val user = Firebase.auth.currentUser
            if (user != null) {
                val test = FavouriteCryptoModel(
                    cryptoDetail.id,
                    cryptoDetail.marketData.currentPrice.usd.toString(),
                    cryptoDetail.lastUpdated
                )
                val database = Firebase.database
                val myRef = database.getReference(user.uid).child("favorite")
                    .child(cryptoDetail.id).setValue(test)
                    .addOnSuccessListener {
                        favoriteImageView.setBackgroundColor(Color.GREEN)
                    }.addOnFailureListener { }
            } else { /*No user is signed in */
            }

        }

    fun removeFavroite(id: String) {
        val user = Firebase.auth.currentUser
        if (user != null) {

            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(id).setValue(null)
                .addOnSuccessListener {
                    isRemoved.value = true
                }.addOnFailureListener {
                    isRemoved.value = false
                }
        } else { /*No user is signed in */
        }
    }

    fun isFavorite(checkId: String) {
        val user = Firebase.auth.currentUser
        if (user != null) {
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(checkId).get().addOnSuccessListener {
                    if (it.value == null) {
                        isFavorite.value = false
                        println(it)
                    } else {
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
/*
        job = null
*/
    }
}