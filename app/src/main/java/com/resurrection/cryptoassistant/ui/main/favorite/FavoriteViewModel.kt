package com.resurrection.cryptoassistant.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.resurrection.cryptoassistant.data.db.CryptoDatabase
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.data.remote.RetrofitClient
import com.resurrection.cryptoassistant.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application):BaseViewModel(application) {

    var cryptoDetail = MutableLiveData<CryptoDetailItem>()
    var disposable: CompositeDisposable? = null
    val dao = CryptoDatabase(getApplication()).cryptoDao()
    var allFavoriteCrypto=  MutableLiveData<List<CryptoMarketModel>>()

    fun getCryptoDetailById(id:String) {
        disposable = RetrofitClient(RetrofitClient.api.getCryptoById(id), this::setVal).returnCompose()

    }

    private fun setVal(any: Any?) {
        cryptoDetail.value = any as CryptoDetailItem
    }

/*    fun insertFavorite(favouriteCryptoModel: FavouriteCryptoModel) = viewModelScope.launch{
            dao.insertFavoriteCrypto(favouriteCryptoModel)
    }*/


    fun getAllFavoriteCrypto() = viewModelScope.launch{
        allFavoriteCrypto!!.value = dao.getCryptoFavorite()

    }



    override fun onCleared() {
        super.onCleared()
/*
        disposable!!.clear()
*/
    }


/*    fun getFavorite() = viewModelScope.launch{
        cryptoDetails.value = dao.getCryptoFavoriteByParameter()
    }

    fun getCryptoById(id:String) = viewModelScope.launch{
        cryptoModel.value = dao.getCryptoById(id)
    }*/

/*    fun getFavoriteListByIds() = viewModelScope.launch{
        cryptoIds.value = dao.getCryptoFavorite()
        var asd: ArrayList<String?>? = ArrayList()
        cryptoIds.value!!.forEach {
            asd!!.add(it.id)
        }
        var qwe :List<String?>?
        qwe = asd

        cryptoModels.value = dao.getFavoriteListByIds(qwe)
    }*/

}