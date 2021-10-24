package com.resurrection.cryptoassistant.ui.main


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.ActivityHomeBinding
import com.resurrection.cryptoassistant.ui.SplashActivity
import com.resurrection.cryptoassistant.ui.auth.AuthActivity
import com.resurrection.cryptoassistant.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityHomeBinding>() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun init(savedInstanceState: Bundle?) {
        setupBottomNavBar()
    }

    private fun setupBottomNavBar() {
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_market, R.id.navigation_favorite, R.id.navigation_support))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

    }

    fun restartApp(context: Context, defaultActivityClass:Class<*>?) {
        val intent = Intent(context, defaultActivityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
        if (context is Activity) {
            context.finish()
            finish()
            finishAffinity()
            System.exit(0)
        }
        Runtime.getRuntime().exit(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_log_out -> {
                FirebaseAuth.getInstance().signOut()
                restartApp(this,AuthActivity::class.java)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}











