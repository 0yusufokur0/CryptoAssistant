package com.resurrection.cryptoassistant.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivityMainBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutRes(): Int { return R.layout.activity_main }

    override fun init(savedInstanceState: Bundle?) {}


}