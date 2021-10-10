package com.resurrection.cryptoassistant.ui.main.market.details

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.databinding.FragmentCryptoDetailBinding
import com.resurrection.cryptoassistant.ui.base.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CryptoDetailFragment(private val mContext: Context) :
    BaseBottomSheetFragment<FragmentCryptoDetailBinding>() {
    var cryptoDetailItem: CryptoDetailItem? = null
    private val viewModel: CryptoDetailViewModel by viewModels()
    var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun init(savedInstanceState: Bundle?) {
        getDetail()
        println("detay açıldı")
        binding.favoriteImageView.changeIconColor(false)
        binding.favoriteImageView.setOnClickListener {
            if (!isFavorite) {
                viewModel.insertFavoriteCrypto(binding.cryptoDetail!!)
                binding.favoriteImageView.changeIconColor(false)
                println("favorilere eklendi")
                isFavorite = true
            } else {
                viewModel.removeFavroite(binding.cryptoDetail!!.id)
                binding.favoriteImageView.changeIconColor(true)

                println("favorilerden çıkarıldı")
                isFavorite = false
                /*this crypto is favorite */
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner, Observer {
            if (it!!) {
/*
                binding.favoriteImageView.setBackgroundColor(Color.GREEN)
*/
                binding.favoriteImageView.changeIconColor(true)
                isFavorite = true
            } else {
/*
                binding.favoriteImageView.setBackgroundColor(Color.RED)
*/
                binding.favoriteImageView.changeIconColor(false)
                isFavorite = false
            }
            println(it)
        })

        viewModel.isRemoved.observe(viewLifecycleOwner, Observer {
            if (it) {
/*
                binding.favoriteImageView.setBackgroundColor(Color.RED)
*/
                binding.favoriteImageView.changeIconColor(false)
            }
        })

        viewModel.firebaseIsSended.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.favoriteImageView.changeIconColor(true)

                } else {
/*
                    binding.favoriteImageView.changeIconColor(false)
*/

                }
            }
        })

    }

    fun getDetail() {
        val data = arguments?.getString("cryptoId")
        viewModel.isFavorite(data!!)
        binding.progressbar.visibility = View.VISIBLE

        viewModel.getCryptoDetailById(data.toString())
        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            binding.cryptoDetail = null
            binding.cryptoDetail = it
            println(it.image.small.toString())
            Glide.with(requireContext()).load(it.image.large).into(binding.imgIconImage)
            binding.progressbar.visibility = View.INVISIBLE
        })
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_crypto_detail
    }

    private infix fun ImageView.changeIconColor(isFavourite: Boolean) {
        val color = if (isFavourite) R.color.green else R.color.red

        this.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(requireContext(), color),
            PorterDuff.Mode.SRC_IN
        )
    }
}


/*var currentCrypto = binding.cryptoDetail!!
var cryptomarketModel = CryptoMarketModel(
    currentCrypto.id,
    currentCrypto.marketData.currentPrice.usd,
    currentCrypto.marketData.highestPrice24h.usd,
    currentCrypto.image.small,
    currentCrypto.lastUpdated,
    currentCrypto.marketData.lowestPrice24h.usd,
    currentCrypto.name,
    currentCrypto.marketData.priceChange24h,
    currentCrypto.marketData.priceChangePercentage24h,
    currentCrypto.symbol
)*/





