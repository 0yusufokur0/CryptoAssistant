package com.resurrection.cryptoassistant.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val viewModel: FavoriteViewModel by viewModels()
    private var cryptoDetailList :List<CryptoDetailItem>? = null
    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorite
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.getFavorite()

        viewModel.cryptoDetails.observe(viewLifecycleOwner, Observer {
            cryptoDetailList = it
            cryptoDetailList!!.forEach {
                println(it.name+"\n")
            }
        })



    }


}