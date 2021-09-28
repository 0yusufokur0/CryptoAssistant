package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.databinding.FragmentCryptoCurrencyBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.market.details.BottomSheetFragment
import kotlinx.coroutines.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.resurrection.cryptoassistant.data.remote.CryptoApiService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoCurrencyFragment : BaseFragment<FragmentCryptoCurrencyBinding>() {

    private var bottomSheet: BottomSheetFragment? = null
    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private var cryptoModels: ArrayList<CryptoMarketModel>? = null
    private var job: Job? = null
    private var adapter: CryptoCurrencyAdapter? = null
    val viewModel1: CryptoCurrencyViewModel by viewModels()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_crypto_currency
    }

    override fun init(savedInstanceState: Bundle?) {
        bottomSheet = BottomSheetFragment(requireContext())
        viewModel1.getAllCrypto()
        viewModel1.allCrypto.observe(viewLifecycleOwner, Observer {
                     it?.let {
            adapter = CryptoCurrencyAdapter(it as ArrayList<CryptoMarketModel>,this::adapterOnCLick)
            binding.cryptoCurrencyRecyclerView.adapter = adapter
        println("asdasdasdasdasd")

    }
        })

    }


    fun handleResponse(asd: Any?) {
        cryptoModels = ArrayList(asd as List<CryptoMarketModel>)

        cryptoModels?.let {
            adapter = CryptoCurrencyAdapter(cryptoModels!!,this::adapterOnCLick)
            binding.cryptoCurrencyRecyclerView.adapter = adapter
    }
           }

    fun adapterOnCLick(cmm :CryptoMarketModel){
    bottomSheet!!.show(childFragmentManager, "Bottom Sheet")

    }

}










