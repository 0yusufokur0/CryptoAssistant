package com.resurrection.cryptoassistant.ui.main.market

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentMarketBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketFragment : BaseFragment<FragmentMarketBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_market
    }

    override fun init(savedInstanceState: Bundle?) {
        val adapter = ViewPagerAdapter(childFragmentManager ,lifecycle)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="CRYPTO CURRENCY"
                }
                1->{
                    tab.text="CATEGORIES"
                }
                2->{
                    tab.text="SIMULATOR"
                }
            }
        }.attach()
    }
}