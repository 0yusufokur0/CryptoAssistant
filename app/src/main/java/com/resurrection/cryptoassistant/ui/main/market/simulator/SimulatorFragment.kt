package com.resurrection.cryptoassistant.ui.main.market.simulator

import android.os.Bundle
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentSimulatorBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimulatorFragment : BaseFragment<FragmentSimulatorBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_simulator

    override fun init(savedInstanceState: Bundle?) {
    }

}