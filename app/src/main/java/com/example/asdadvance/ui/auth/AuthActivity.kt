package com.example.asdadvance.ui.auth

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.changePage
import com.example.asdadvance.databinding.ActivityAuthBinding
import com.example.asdadvance.ui.BagicodeTravel
import com.example.asdadvance.ui.main.MainActivity


class AuthActivity : BaseBindingActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        if(!BagicodeTravel.getApp().getToken().isNullOrEmpty()) {
            changePage(MainActivity::class.java,null, true)
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}