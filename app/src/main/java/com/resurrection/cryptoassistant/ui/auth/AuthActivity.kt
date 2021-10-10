package com.resurrection.cryptoassistant.ui.auth

import android.os.Bundle
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivityAuthBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity


class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_auth
    }

    override fun init(savedInstanceState: Bundle?) {
        this.supportFragmentManager
            .beginTransaction().setCustomAnimations(R.anim.right_to_left_second, R.anim.right_to_left_first)
            .replace(R.id.authFrameLayout, LoginFragment())
            .addToBackStack(null)
            .commit()
    }
}