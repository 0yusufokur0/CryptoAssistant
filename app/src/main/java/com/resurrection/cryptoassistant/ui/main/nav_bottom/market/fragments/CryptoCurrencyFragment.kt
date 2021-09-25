package com.resurrection.cryptoassistant.ui.main.nav_bottom.market.fragments

import android.os.Bundle
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CoinDetailItem
import com.resurrection.cryptoassistant.data.remote.RetrofitClient
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.databinding.FragmentCryptoCurrencyBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.nav_bottom.market.adapters.CryptoMarketAdapter
import kotlinx.coroutines.*


class CryptoCurrencyFragment : BaseFragment<FragmentCryptoCurrencyBinding>() {

    private var bottomSheet: BottomSheetFragment? = null

    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private var cryptoModels: ArrayList<CryptoMarketModel>? = null
    private var job: Job? = null
    private var adapter: CryptoMarketAdapter? = null


    override fun getLayoutRes(): Int {
        return R.layout.fragment_crypto_currency
    }

    override fun init(savedInstanceState: Bundle?) {
        bottomSheet = BottomSheetFragment(requireContext())


        RetrofitClient(RetrofitClient.api.getData(),this::handleResponse)


    }


    fun handleResponse(asd: Any?) {
        cryptoModels = ArrayList(asd as List<CryptoMarketModel>)

        cryptoModels?.let {
            adapter = CryptoMarketAdapter(cryptoModels!!,this::adapterOnCLick)
            binding.cryptoCurrencyRecyclerView.adapter = adapter


    }

           }

    fun adapterOnCLick(cmm :CryptoMarketModel){
        RetrofitClient(RetrofitClient.api.getCryptoByID(cmm.coinId), {bottomSheet?.fetchData(it as CoinDetailItem)})
        bottomSheet!!.show(childFragmentManager, "Bottom Sheet")


    }

/*    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApiService::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            adapter = CryptoMarketAdapter(
                                cryptoModels!!,
                                this@CryptoCurrencyFragment::adaterItemOnClick
                            )
                            binding.cryptoCurrencyRecyclerView.adapter = adapter

                        }
                    }
                }
            }
        }
    }*/


}








