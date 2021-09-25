package com.resurrection.cryptoassistant.data.remote

import io.reactivex.disposables.CompositeDisposable
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class RetrofitClient(
    var anyObservable: Observable<*>, function: (Any?) -> Unit) {
    var compositeDisposable: CompositeDisposable

    companion object {
        val api: CryptoApiService
            get() {
                val BASE_URL = "https://api.coingecko.com/api/v3/"
                val gson = GsonBuilder().setLenient().create()
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                return retrofit.create(CryptoApiService::class.java)
            }
    }

    init {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            anyObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(::print)
                .subscribe { any -> function.invoke(any as Any?) })
    }
    fun returnCompose(): CompositeDisposable {
        return compositeDisposable
    }
}






