package com.resurrection.cryptoassistant.ui.main.market.details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.data.model.CryptoMarketModel
import com.resurrection.cryptoassistant.data.model.FavouriteCryptoModel
import com.resurrection.cryptoassistant.databinding.BottomSheetFragmentBinding
import com.resurrection.cryptoassistant.ui.base.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CryptoDetailFragment(private val mContext: Context) :
    BaseBottomSheetFragment<BottomSheetFragmentBinding>() {
    var cryptoDetailItem: CryptoDetailItem? = null
    private val viewModel: CryptoDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

    }

    override fun init(savedInstanceState: Bundle?) {
        getDetail()


        binding.favoriteImageView.setOnClickListener {
            val user = Firebase.auth.currentUser
            if (user != null) {

                val test = FavouriteCryptoModel(
                    binding.cryptoDetail!!.id,
                    binding.cryptoDetail!!.marketData.currentPrice.usd.toString(),
                    binding.cryptoDetail!!.lastUpdated
                )
                val db = FirebaseFirestore.getInstance()
                db.collection(user.uid)
                    .add(test)
                    .addOnSuccessListener { documentReference ->
                        // get crypto  market data from detail api
                        var currentCrypto = binding.cryptoDetail!!
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
                        )
                        viewModel.insertFavoriteCrypto(cryptomarketModel)
                    }
                    .addOnFailureListener { e ->

                    }

            } else {
                // No user is signed in
            }


        }


    }


    override fun getLayoutRes(): Int {
        return R.layout.bottom_sheet_fragment
    }

    fun getDetail() {
        val data = arguments?.getString("cryptoId")
        binding.progressbar.visibility = View.VISIBLE

        viewModel.getCryptoDetailById(data.toString())
        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            binding.cryptoDetail = null
            binding.cryptoDetail = it
            println(it.image.small.toString())
            Glide.with(requireContext()).load(it.image.large).into(binding.imgIconImage)
            binding.progressbar.visibility = View.INVISIBLE
            binding.favoriteImageView.setBackgroundColor(Color.RED)
        })
    }


}






