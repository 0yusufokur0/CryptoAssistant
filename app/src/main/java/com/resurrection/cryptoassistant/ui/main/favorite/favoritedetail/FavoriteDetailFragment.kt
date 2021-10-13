package com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteDetailBinding
import com.resurrection.cryptoassistant.ui.base.BaseBottomSheetFragment
import com.resurrection.cryptoassistant.ui.main.favorite.chart.CryptoChartFragment
import com.resurrection.cryptoassistant.util.setCryptoPriceBackground
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class FavoriteDetailFragment : BaseBottomSheetFragment<FragmentFavoriteDetailBinding>() {

    private val viewModel : FavoriteDetailViewModel by viewModels()

    override fun getLayoutRes(): Int { return R.layout.fragment_favorite_detail }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        this.childFragmentManager
            .beginTransaction().setCustomAnimations(R.anim.right_to_left_second, R.anim.right_to_left_first)
            .replace(R.id.chartFrameLayout, CryptoChartFragment())
            .addToBackStack(null)
            .commit()
    }
    override fun init(savedInstanceState: Bundle?) {
        val data = arguments?.getString("cryptoId")
        viewModel.getCryptoDetailById(data!!)

        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            binding.cryptoDetail = it
            viewModel.getCryptoByFirebase(data!!)
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())

            binding.cryptoDetail!!.lastUpdated = currentDate.toString()

        })
        viewModel.cryptoFromFirebase.observe(viewLifecycleOwner, Observer {
            binding.favoriteCrypto = it
            binding.favoriteCrypto?.let {
                binding.cryptoDetail?.let {
                    var priceDifference:Double = binding.cryptoDetail!!.marketData.currentPrice.usd.toDouble() - binding.favoriteCrypto!!.currentPrice.toDouble()
                    var tempString :String = String.format("%.2f", priceDifference)
                    binding.priceDifference.text = " $tempString "
                    binding.priceDifference.setCryptoPriceBackground(priceDifference)
                }
            }

        })

    }


}