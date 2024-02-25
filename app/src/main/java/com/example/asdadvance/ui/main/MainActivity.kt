package com.example.asdadvance.ui.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.example.asdadvance.R
import com.example.asdadvance.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navcontroller: NavController

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding

    }

    override fun onBindView() {
        navcontroller = findNavController(R.id.nav_host_fragment_activity_main)

        binding.navView.setupWithNavController(navcontroller)
        binding.navView.setOnNavigationItemSelectedListener {
            if (it.itemId != binding.navView.selectedItemId)
                NavigationUI.onNavDestinationSelected(it, navcontroller)
            true
        }

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_mybooking, R.id.navigation_notifications, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
}