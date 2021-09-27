package com.resurrection.cryptoassistant.ui.main


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivityHomeBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity


class HomeActivity : BaseActivity<ActivityHomeBinding>()  {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getLayoutRes(): Int { return R.layout.activity_home }

    override fun init(savedInstanceState: Bundle?) {
        setupBottomNavBar()
    }

    private fun setupBottomNavBar(){
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_market, R.id.navigation_favorite, R.id.navigation_support))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}











