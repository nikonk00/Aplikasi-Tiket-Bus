package com.example.asdadvance.ui.main.mybooking

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.changePage

import com.example.asdadvance.databinding.FragmentMybookingBinding
import com.example.asdadvance.model.response.LoginResponse
import com.example.asdadvance.model.response.MyBookingResponse
import com.example.asdadvance.ui.BagicodeTravel
import com.example.asdadvance.ui.main.mybooking.detail.MybookingDetailActivity
import com.google.gson.Gson

class MybookingFragment : BaseBindingFragment(), MyBookingAdapter.ItemAdapterCallback,
    MyBookingContract.View {

    private lateinit var binding : FragmentMybookingBinding
    private lateinit var presenter: MyBookingPresenter

    override fun getFragmentView(): ViewBinding {
        binding = FragmentMybookingBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = MyBookingPresenter(this)

        var user  = BagicodeTravel.getApp().getUser()
        var userResponse = Gson().fromJson(user, LoginResponse::class.java)

        userResponse.idUser?.let { presenter.getMyBookingList(it) }
    }

    override fun onMyBookingSuccess(response: ArrayList<MyBookingResponse>) {
        var adapter = MyBookingAdapter(response, this)
        val layoutManager= LinearLayoutManager(activity)
        binding.rvMybooking.layoutManager = layoutManager
        binding.rvMybooking.adapter = adapter
    }

    override fun onMyBookingFailed(message: String) {
        showSnackbarMessage(binding.rvMybooking, message, Const.ToastType.Error)
    }

    override fun onListMyBookingClick(v: View, data: MyBookingResponse) {
        var bundle = Bundle()
        bundle.putParcelable("data", data)
        changePage(MybookingDetailActivity::class.java, bundle, requireActivity(), false)

    }
}