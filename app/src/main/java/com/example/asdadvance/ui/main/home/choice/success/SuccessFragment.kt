package com.example.asdadvance.ui.main.home.choice.success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.example.asdadvance.R
import com.example.asdadvance.databinding.FragmentSuccessBinding

class SuccessFragment : BaseBindingFragment() {

    lateinit var binding: FragmentSuccessBinding

    override fun getFragmentView(): ViewBinding {
        binding = FragmentSuccessBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.btnSuccess.setOnClickListener {
            requireActivity().finish()
        }

    }

}