package com.resurrection.cryptoassistant.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.*
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivitySplashBinding
import com.resurrection.cryptoassistant.ui.auth.AuthActivity
import com.resurrection.cryptoassistant.ui.base.BaseActivity
import com.resurrection.cryptoassistant.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(), Animation.AnimationListener {

    lateinit var animationImageView: Animation

    override fun getLayoutRes(): Int { return R.layout.activity_splash }

    override fun init(savedInstanceState: Bundle?) {
        animationImageView = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
        animationImageView.setAnimationListener(this)
        binding.splashTextView.startAnimation(animationImageView)





    }


    override fun onAnimationStart(p0: Animation?) {}

    override fun onAnimationEnd(p0: Animation?) {
        if (p0 == animationImageView) {
            binding.splashTextView.visibility = View.INVISIBLE
            startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in)
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {}


}