package com.resurrection.cryptoassistant.ui.auth

import android.os.Bundle
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivityAuthBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity





class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun getLayoutRes(): Int { return R.layout.activity_auth }

    override fun init(savedInstanceState: Bundle?) {
/*
        loadFragment(R.id.authFrameLayout,LoginFragment()) }
*/

        this.supportFragmentManager
            .beginTransaction().setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
            .replace(R.id.authFrameLayout, LoginFragment())
            .addToBackStack(null)
            .commit()


    }
}