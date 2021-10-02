package com.resurrection.cryptoassistant.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.*
import com.google.firebase.auth.FirebaseAuth
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivitySplashBinding
import com.resurrection.cryptoassistant.ui.auth.AuthActivity
import com.resurrection.cryptoassistant.ui.base.BaseActivity
import com.resurrection.cryptoassistant.ui.main.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(), Animation.AnimationListener {

    lateinit var animationImageView: Animation
    private var loginState = false

    override fun getLayoutRes(): Int { return R.layout.activity_splash }

    override fun init(savedInstanceState: Bundle?) {
        if (FirebaseAuth.getInstance().currentUser != null){
            loginState = true
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in)
        }else{
            animationImageView = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
            animationImageView.setAnimationListener(this)
            binding.splashTextView.startAnimation(animationImageView)
        }



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