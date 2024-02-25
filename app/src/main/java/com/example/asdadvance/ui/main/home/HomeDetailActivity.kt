package com.example.asdadvance.ui.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.example.asdadvance.R
import com.example.asdadvance.databinding.ActivityHomeDetailBinding

class HomeDetailActivity : BaseBindingActivity() {

    private lateinit var binding : ActivityHomeDetailBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.checkout_host_fragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
        }
    }

}