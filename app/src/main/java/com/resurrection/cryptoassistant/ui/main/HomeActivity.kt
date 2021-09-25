package com.resurrection.cryptoassistant.ui.main

import android.content.ClipData
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.resurrection.cryptoassistant.databinding.ActivityHomeBinding
import com.resurrection.cryptoassistant.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.navigation.NavigationBarView
import com.resurrection.cryptoassistant.R
import com.resurrection.navdraver.ui.gallery.GalleryFragment


import com.resurrection.navdraver.ui.home.HomeFragment
import com.resurrection.navdraver.ui.slideshow.SlideshowFragment
import android.util.DisplayMetrics
import android.view.*
import androidx.core.content.res.ResourcesCompat


class HomeActivity : BaseActivity<ActivityHomeBinding>(),NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener  {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getLayoutRes(): Int { return R.layout.activity_home }

    override fun init(savedInstanceState: Bundle?) {

        setupBottomNavBar()
        setupNavDrawer()






    }

    private fun setupBottomNavBar(){
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_market, R.id.navigation_favorite, R.id.navigation_support))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        binding.navView.setOnItemSelectedListener(this)
    }

    private fun setupNavDrawer(){
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView1
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            /* Navigation Drawer */
            R.id.nav_home ->{
                binding.container.visibility = View.GONE
                binding.navDrawerContainer.visibility = View.VISIBLE
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.nav_home)

            }
            R.id.nav_gallery ->{
                binding.container.visibility = View.GONE
                binding.navDrawerContainer.visibility = View.VISIBLE
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.nav_gallery)
            }
            R.id.nav_slideshow ->{
                binding.container.visibility = View.GONE
                binding.navDrawerContainer.visibility = View.VISIBLE
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.nav_slideshow)
            }

            /* Bottom Navigation Bar */

            R.id.navigation_market ->{
                binding.container.visibility = View.VISIBLE
                binding.navDrawerContainer.visibility = View.GONE
                val navController = findNavController(R.id.nav_host_fragment_activity_home)
                navController.navigate(R.id.navigation_market)
            }

            R.id.navigation_favorite ->{
                binding.container.visibility = View.VISIBLE
                binding.navDrawerContainer.visibility = View.GONE
                val navController = findNavController(R.id.nav_host_fragment_activity_home)
                navController.navigate(R.id.navigation_favorite)
            }
            R.id.navigation_support ->{
                binding.container.visibility = View.VISIBLE
                binding.navDrawerContainer.visibility = View.GONE
                val navController = findNavController(R.id.nav_host_fragment_activity_home)
                navController.navigate(R.id.navigation_support)
            }


        }

        return true
    }



}










/*
private fun loadFragment(fragment: Fragment?,container:Int): Boolean {
    if (fragment != null) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .commit()
        return true
    }
    return false
}
*/
