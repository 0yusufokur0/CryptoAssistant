package com.resurrection.cryptoassistant.ui.main.nav_bottom.market.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CoinDetailItem
import com.resurrection.cryptoassistant.databinding.BottomSheetFragmentBinding
import com.resurrection.cryptoassistant.ui.base.BaseBottomSheetFragment

//TODO: Bottom shhet için base class yazılacak
class BottomSheetFragment(private val mContext: Context) :
    BaseBottomSheetFragment<BottomSheetFragmentBinding>() {
    var coinDetailItem: CoinDetailItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }  override fun init(savedInstanceState: Bundle?) {
        coinDetailItem?.let {
            println(it.name)
        }    }

    fun fetchData(detail: CoinDetailItem) {
        coinDetailItem = detail
    }

    override fun getLayoutRes(): Int {
    return R.layout.bottom_sheet_fragment
    }


}






