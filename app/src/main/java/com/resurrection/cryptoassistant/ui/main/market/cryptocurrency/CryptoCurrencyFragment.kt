package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.databinding.FragmentCryptoCurrencyBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.market.details.CryptoDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class CryptoCurrencyFragment : BaseFragment<FragmentCryptoCurrencyBinding>() {

    private var cryptoDetail: CryptoDetailFragment? = null
    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private var cryptoModels: ArrayList<CryptoMarketModel>? = null
    private var job: Job? = null
    private var adapter: CryptoCurrencyAdapter? = null
    val viewModel: CryptoCurrencyViewModel by viewModels()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_crypto_currency
    }

    override fun init(savedInstanceState: Bundle?) {
        cryptoDetail = CryptoDetailFragment(requireContext())
        viewModel.getAllCrypto()
        viewModel.allCrypto.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter = CryptoCurrencyAdapter(it as ArrayList<CryptoMarketModel>, this::adapterOnCLick)
                binding.cryptoCurrencyRecyclerView.adapter = adapter
            }
        })
    }

    fun adapterOnCLick(cmm: CryptoMarketModel) {
        val bundle = Bundle()
        bundle.putString("cryptoId", cmm.cryptoId.toString())
        cryptoDetail!!.arguments = bundle
        cryptoDetail!!.show(childFragmentManager, "Bottom Sheet")
    }
}










