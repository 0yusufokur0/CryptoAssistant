package com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail

import android.app.Application
import android.graphics.Color
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
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
class FavoriteDetailViewModel @Inject constructor(val cryptoRepository: TestRepository) :
    BaseViewModel() {
    private var _cryptoDetail = MutableLiveData<Resource<CryptoDetailItem>>()
    private var _cryptoFromFirebase = MutableLiveData<FavouriteCryptoModel>()
    private var _isFavorite = MutableLiveData<Boolean?>()
    private var _isRemoved = MutableLiveData<Boolean>()

    val cryptoDetail : LiveData<Resource<CryptoDetailItem>> = _cryptoDetail
    val cryptoFromFirebase : LiveData<FavouriteCryptoModel>  = _cryptoFromFirebase
    val isFavorite : LiveData<Boolean?> = _isFavorite
    val isRemoved : LiveData<Boolean> = _isRemoved
    private val user = Firebase.auth.currentUser

    fun getCryptoDetailById(id: String) = viewModelScope.launch {
        cryptoRepository.getCryptoDetailById(id)
            .onStart {

            }.catch {

            }.collect {
                _cryptoDetail.postValue(it)
            }

    }
    fun getCryptoByFirebase(id:String){
        if (user != null) {
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(id).get()
                .addOnSuccessListener {
                    _cryptoFromFirebase.value = it.getValue(FavouriteCryptoModel::class.java)

                }.addOnFailureListener { }
        } else { /*No user is signed in */
        }
    }

    fun insertFavoriteCrypto(cryptoDetail: CryptoDetailItem, favoriteImageView: ImageView) =
        viewModelScope.launch {
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
                    _isRemoved.value = true
                }.addOnFailureListener {
                    _isRemoved.value = false
                }
        } else { /*No user is signed in */
        }
    }

    fun isFavorite(checkId: String) {
        if (user != null) {
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(checkId).get().addOnSuccessListener {
                    if (it.value == null) {
                        _isFavorite.value = false
                        println(it)
                    } else {
                        _isFavorite.value = true
                    }

                    println(it)
                }.addOnFailureListener {
                    _isFavorite.value = false
                }

        } else { // No user is signed in }
        }
    }


}