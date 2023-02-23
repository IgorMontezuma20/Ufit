package com.app.ufit.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.ufit.R
import com.app.ufit.databinding.ActivityHomeBinding
import com.app.ufit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.navHostFragment) as NavHostFragment
//        navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment,
//                R.id.favoriteExercisesFragment,
//                R.id.profileFragment
//            )
//        )
//
////        binding.bottomNavigationView.setupWithNavController(navController)
////        setupActionBarWithNavController(navController, appBarConfiguration)
//
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
    }
}