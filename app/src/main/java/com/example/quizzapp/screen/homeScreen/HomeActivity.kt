package com.example.quizzapp.screen.homeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var homeBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)



        // Tạo NavController từ NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_home_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Thiết lập mục được chọn mặc định là "Home"
        homeBinding.bottomNavHome.selectedItemId = R.id.homeFragment

        homeBinding.bottomNavHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    Log.d("Item Home", item.itemId.toString())
                    navController.navigate(R.id.homeFragment)
                    item.setIcon(R.drawable.icon_home_fill)
                    homeBinding.bottomNavHome.menu.findItem(R.id.controllerFragment).setIcon(R.drawable.icon_controller)
                    homeBinding.bottomNavHome.menu.findItem(R.id.profileFragment).setIcon(R.drawable.icon_proflie_outline)
                }
                R.id.controllerFragment -> {
                    Log.d("Item Controller", item.itemId.toString())
                    navController.navigate(R.id.controllerFragment)
                    item.setIcon(R.drawable.icon_controller_fill)
                    homeBinding.bottomNavHome.menu.findItem(R.id.homeFragment).setIcon(R.drawable.icon_home_outline)
                    homeBinding.bottomNavHome.menu.findItem(R.id.profileFragment).setIcon(R.drawable.icon_proflie_outline)
                }
                R.id.profileFragment -> {
                    Log.d("Item Profile", item.itemId.toString())

                    navController.navigate(R.id.profileFragment)
                    item.setIcon(R.drawable.icon_profile_fill)
                    homeBinding.bottomNavHome.menu.findItem(R.id.homeFragment).setIcon(R.drawable.icon_home_outline)
                    homeBinding.bottomNavHome.menu.findItem(R.id.controllerFragment).setIcon(R.drawable.icon_controller)
                }
            }
            true
        }

//        homeBinding.bottomNavHome.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//
//                R.id.homeFragment -> {
//                    Log.d("Item Home", item.itemId.toString())
//                    navController.navigate(R.id.homeFragment)
//                    item.setIcon(R.drawable.icon_home_fill)
//                    homeBinding.bottomNavHome.menu.findItem(R.id.controllerFragment).setIcon(R.drawable.icon_controller)
//                    homeBinding.bottomNavHome.menu.findItem(R.id.profileFragment).setIcon(R.drawable.icon_proflie_outline)
//                }
//                R.id.controllerFragment -> {
//                    Log.d("Item Controller", item.itemId.toString())
//                    navController.navigate(R.id.controllerFragment)
//                    item.setIcon(R.drawable.icon_controller_fill)
//                    homeBinding.bottomNavHome.menu.findItem(R.id.homeFragment).setIcon(R.drawable.icon_home_outline)
//                    homeBinding.bottomNavHome.menu.findItem(R.id.profileFragment).setIcon(R.drawable.icon_proflie_outline)
//                }
//                R.id.profileFragment -> {
//                    Log.d("Item Profile", item.itemId.toString())
//
//                    navController.navigate(R.id.profileFragment)
//                    item.setIcon(R.drawable.icon_profile_fill)
//                    homeBinding.bottomNavHome.menu.findItem(R.id.homeFragment).setIcon(R.drawable.icon_home_outline)
//                    homeBinding.bottomNavHome.menu.findItem(R.id.controllerFragment).setIcon(R.drawable.icon_controller)
//                }
//            }
//            true
//        }
    }
}