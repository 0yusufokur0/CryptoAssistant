package com.resurrection.cryptoassistant.ui.main.market.details

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.data.model.CryptoDetailItem
import com.resurrection.cryptoassistant.databinding.BottomSheetFragmentBinding
import com.resurrection.cryptoassistant.ui.base.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment(private val mContext: Context) :
    BaseBottomSheetFragment<BottomSheetFragmentBinding>() {
    var cryptoDetailItem: CryptoDetailItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }  override fun init(savedInstanceState: Bundle?) {
        cryptoDetailItem?.let {
            println(it.name)
        }    }

    fun fetchData(detail: CryptoDetailItem) {
        cryptoDetailItem = detail
    }

    override fun getLayoutRes(): Int {
    return R.layout.bottom_sheet_fragment
    }


}






