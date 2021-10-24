package com.resurrection.cryptoassistant.ui.main.market.cryptocurrency

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.resurrection.cryptoassistant.BR
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.databinding.CryptoCurrencyItemBinding
import com.resurrection.cryptoassistant.databinding.FragmentCryptoCurrencyBinding
import com.resurrection.cryptoassistant.ui.base.BaseAdapter
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.market.details.CryptoDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoCurrencyFragment : BaseFragment<FragmentCryptoCurrencyBinding>() {

    private var cryptoDetail: CryptoDetailFragment? = null
    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private var cryptoModels: ArrayList<CryptoMarketModel>? = null
    private var adapter: BaseAdapter<CryptoMarketModel,CryptoCurrencyItemBinding>? = null
    val viewModel: CryptoCurrencyViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.fragment_crypto_currency

    override fun init(savedInstanceState: Bundle?) {
        cryptoDetail = CryptoDetailFragment(requireContext())
        viewModel.getAllCrypto()
        viewModel.allCrypto.observe(viewLifecycleOwner, Observer {
            it?.data?.let {
                binding.cryptoCurrencyRecyclerView.adapter =
                    BaseAdapter<CryptoMarketModel, CryptoCurrencyItemBinding>(
                        R.layout.crypto_currency_item,
                        it as ArrayList<CryptoMarketModel>,
                        BR.crypto,
                        this::adapterOnCLick
                    )
            }
        })
    }

    private fun adapterOnCLick(cmm: CryptoMarketModel) {
        val bundle = Bundle()
        bundle.putString("cryptoId", cmm.cryptoId.toString())
        cryptoDetail!!.arguments = bundle
        cryptoDetail!!.show(childFragmentManager, "Bottom Sheet")
    }
}










