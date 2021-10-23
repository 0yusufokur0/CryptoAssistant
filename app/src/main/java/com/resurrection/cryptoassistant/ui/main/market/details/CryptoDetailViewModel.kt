package com.resurrection.cryptoassistant.ui.main.market.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.data.repository.TestRepository
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import com.resurrection.cryptoassistant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CryptoDetailViewModel @Inject constructor(val cryptoRepository: TestRepository) :
    BaseViewModel() {

    private var _cryptoDetail = MutableLiveData<Resource<CryptoDetailItem>>()
    private var _isFavorite = MutableLiveData<Boolean?>()
    private var _firebaseIsSended = MutableLiveData<Boolean?>()
    private var _isRemoved = MutableLiveData<Boolean>()

    var cryptoDetail : MutableLiveData<Resource<CryptoDetailItem>> = _cryptoDetail
    var isFavorite : MutableLiveData<Boolean?> = _isFavorite
    var firebaseIsSended : MutableLiveData<Boolean?> = _firebaseIsSended
    var isRemoved : MutableLiveData<Boolean> = _isRemoved

    fun getCryptoDetailById(id: String) = viewModelScope.launch {
        cryptoRepository.getCryptoDetailById(id)
            .onStart {
                // Loading Animation
            }.catch {
                // fail load
            }.collect {
                cryptoDetail.postValue(it)
            }
    }

    fun insertFavoriteCrypto(cryptoDetail: CryptoDetailItem) {
        val user = Firebase.auth.currentUser
        if (user != null) {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())

            val test = FavouriteCryptoModel(
                cryptoDetail.id,
                cryptoDetail.marketData.currentPrice.usd.toString(),
                currentDate
            )
            val database = Firebase.database
            val myRef = database.getReference(user.uid).child("favorite")
                .child(cryptoDetail.id).setValue(test)
                .addOnSuccessListener {
                    firebaseIsSended.value = true
                }.addOnFailureListener {
                    firebaseIsSended.value = false
                }
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
                }.addOnFailureListener { isFavorite.value = false }

        } else { // No user is signed in }
        }
    }

}