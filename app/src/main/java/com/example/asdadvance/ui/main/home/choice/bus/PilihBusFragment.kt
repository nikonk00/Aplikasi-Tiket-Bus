package com.example.asdadvance.ui.main.home.choice.bus

import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.example.asdadvance.R
import com.example.asdadvance.databinding.FragmentPilihBusBinding
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.ui.dialog.bottomsheet.ListTipeBusBottomSheet


class PilihBusFragment : BaseBindingFragment(), PilihBusAdapter.ItemAdapterCallback,
    PilihBusContract.View {

    private  var binding : FragmentPilihBusBinding ?=null
    private lateinit var presenter: PilihBusPresenter

    private lateinit var busRequest: BusRequest

    override fun getFragmentView(): ViewBinding {
        if (binding == null) {
            binding = FragmentPilihBusBinding.inflate(layoutInflater)
        }
        return binding as FragmentPilihBusBinding
    }

    override fun onBindView() {
        presenter = PilihBusPresenter(this)
        var busRequestArgs = arguments?.getParcelable<BusRequest>("data")
        busRequestArgs?.let {
            presenter.getBusList(it)
            busRequest = it
        }

        var dataTipeBus = ArrayList<String>()
        dataTipeBus.add("Semua")
        dataTipeBus.add("Bisnis")
        dataTipeBus.add("Ekonomi")

        binding?.ivFilter?.setOnClickListener{
            ListTipeBusBottomSheet.newInstance(object : ListTipeBusBottomSheet.Listener{
                override fun onClick(data: String) {
                    presenter.getBusList(busRequest.apply {
                        tipe = data
                    })
                }
            }, 0,
                dataTipeBus,
                "Filter Tipe Bus",
                "Gunakan filter untuk mempermudah pencarian")
                .show(parentFragmentManager, "")
        }
    }

    override fun onBusSuccess(BusResponse: ArrayList<BusResponse>) {
        var adapter = PilihBusAdapter(BusResponse, this)
        val layoutManager = LinearLayoutManager(activity)

        binding?.rvBus?.layoutManager = layoutManager
        binding?.rvBus?.adapter = adapter

    }

    override fun onBusFailed(message: String) {
        binding?.rvBus?.let { showSnackbarMessage(it, message, Const.ToastType.Error) }
    }

    override fun onListPilihBusClick(v: View, data: BusResponse) {
        var bundle = Bundle()
        bundle.putParcelable("data", data)
        bundle.putParcelable("dataPick", busRequest)
        Navigation.findNavController(v)
            .navigate(R.id.fragmentPilihKursi, bundle)

    }

}