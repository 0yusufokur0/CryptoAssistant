package com.resurrection.cryptoassistant.ui.main.market.categories

import android.os.Bundle
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentCategoriesBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_categories

    override fun init(savedInstanceState: Bundle?) {

    }


}