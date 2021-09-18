package com.resurrection.cryptoassistant.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivitySplashBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity
import com.resurrection.cryptoassistant.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(), Animation.AnimationListener {

    lateinit var animationImageView: Animation

    override fun getLayoutRes(): Int { return R.layout.activity_splash }

    override fun init(savedInstanceState: Bundle?) {
        animationImageView = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
        animationImageView.setAnimationListener(this)
        binding.splashTextView.setOnClickListener {
            binding.splashTextView.startAnimation(animationImageView)
        }

        binding.splashTextView

    }


    override fun onAnimationStart(p0: Animation?) {}

    override fun onAnimationEnd(p0: Animation?) {
        if (p0 == animationImageView) {
            binding.splashTextView.visibility = View.INVISIBLE
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in)
        }
    }
    override fun onAnimationRepeat(p0: Animation?) {}




}