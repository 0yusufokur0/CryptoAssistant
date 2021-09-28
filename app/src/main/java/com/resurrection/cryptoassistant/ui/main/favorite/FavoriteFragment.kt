package com.resurrection.cryptoassistant.ui.main.favorite

import android.os.Bundle
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentFavoriteBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorite
    }

    override fun init(savedInstanceState: Bundle?) {

    }


}