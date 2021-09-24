package com.resurrection.cryptoassistant.ui.main.ui.market.fragments

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.resurrection.cryptoassistant.R
import android.widget.Toast

class BottomSheetFragment(private val mContext: Context) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.asd)
        button.setOnClickListener { Toast.makeText(mContext, "sdfsdf", Toast.LENGTH_SHORT).show() }
    }
}