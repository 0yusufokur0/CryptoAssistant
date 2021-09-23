package com.resurrection.cryptoassistant.ui.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivityHomeBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {


    override fun getLayoutRes(): Int { return R.layout.activity_home }

    override fun init(savedInstanceState: Bundle?) {
        setupNavBar()


    }

    private fun setupNavBar(){
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_market, R.id.navigation_favorite, R.id.navigation_support))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }
}