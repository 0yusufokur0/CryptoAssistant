package com.resurrection.cryptoassistant.ui.main.market.details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
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
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }  override fun init(savedInstanceState: Bundle?) {
        val data =  arguments?.getString("cryptoId")
        binding.progressbar.visibility = View.VISIBLE

        viewModel.getCryptoById(data.toString())
        viewModel.cryptoDetail.observe(viewLifecycleOwner, Observer {
            binding.cryptoDetail = null
            binding.cryptoDetail = it
            println(it.image.small.toString())
            Glide.with(requireContext()).load(it.image.large).into(binding.imgIconImage)
            binding.progressbar.visibility = View.INVISIBLE
            binding.favoriteImageView.setBackgroundColor(Color.RED)
        })


        binding.favoriteImageView.setOnClickListener {
            viewModel.insertFavorite(binding.cryptoDetail as CryptoDetailItem)

        }



    }



    override fun getLayoutRes(): Int {
    return R.layout.bottom_sheet_fragment
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}






