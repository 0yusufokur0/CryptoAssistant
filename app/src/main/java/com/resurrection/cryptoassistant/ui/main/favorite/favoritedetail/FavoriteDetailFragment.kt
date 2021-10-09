package com.resurrection.cryptoassistant.ui.main.favorite.favoritedetail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteDetailBinding
import com.resurrection.cryptoassistant.ui.base.BaseBottomSheetFragment
import com.resurrection.cryptoassistant.ui.main.market.details.CryptoDetailViewModel
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

    }
    override fun init(savedInstanceState: Bundle?) {
        val data = arguments?.getString("cryptoId")
        viewModel.getCryptoDetailById(data!!)
        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            viewModel.getCryptoByFirebase(data!!)
            binding.cryptoDetail = it
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())

            binding.cryptoDetail!!.lastUpdated = currentDate.toString()

        })
        viewModel.cryptoFromFirebase.observe(viewLifecycleOwner, Observer {
            binding.favoriteCrypto = it

        })

    }


}