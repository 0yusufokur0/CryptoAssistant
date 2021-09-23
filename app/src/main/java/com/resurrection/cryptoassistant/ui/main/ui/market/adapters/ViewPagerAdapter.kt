package com.resurrection.cryptoassistant.ui.main.ui.market.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.resurrection.cryptoassistant.ui.main.ui.market.fragments.CategoriesFragment
import com.resurrection.cryptoassistant.ui.main.ui.market.fragments.CryptoCurrencyFragment
import com.resurrection.cryptoassistant.ui.main.ui.market.fragments.SimulatorFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                CryptoCurrencyFragment()
            }
            1->{
                CategoriesFragment()
            }
            2->{
                SimulatorFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}