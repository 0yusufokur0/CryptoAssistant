package com.resurrection.cryptoassistant.ui.main.market

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.resurrection.cryptoassistant.ui.main.market.categories.CategoriesFragment
import com.resurrection.cryptoassistant.ui.main.market.cryptocurrency.CryptoCurrencyFragment
import com.resurrection.cryptoassistant.ui.main.market.simulator.SimulatorFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CryptoCurrencyFragment()
            }
            1 -> {
                CategoriesFragment()
            }
            2 -> {
                SimulatorFragment()
            }
            else -> {
                Fragment()
            }

        }
    }
}