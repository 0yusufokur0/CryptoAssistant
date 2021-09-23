package com.resurrection.cryptoassistant.ui.main.ui.market

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentMarketBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.ui.market.adapters.ViewPagerAdapter

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
                    tab.text="First"
                }
                1->{
                    tab.text="Second"
                }
                2->{
                    tab.text="Third"
                }
            }
        }.attach()


    }

}