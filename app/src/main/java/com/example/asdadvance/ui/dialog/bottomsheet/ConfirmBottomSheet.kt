package com.example.asdadvance.ui.dialog.bottomsheet

import android.app.Dialog
import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingBottomSheet
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.example.asdadvance.R
import com.example.asdadvance.databinding.BottomSheetConfirmBinding
import com.example.asdadvance.databinding.BottomSheetListTerminalBinding
import com.example.asdadvance.model.TerminalModel
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.KursiResponse
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomPenumpangAdapter
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomTerminalAdapter
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomTipeBusAdapter
import java.text.FieldPosition

class ConfirmBottomSheet : BaseBindingBottomSheet(){

    private var listerner : Listener ?= null
    private var dialogPosition : Int = 0

    private lateinit var binding : BottomSheetConfirmBinding
    private lateinit var dataKursi : ArrayList<KursiResponse>
    private lateinit var dataBus : BusResponse

    override fun getFragmentView(): ViewBinding {
        binding = BottomSheetConfirmBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.tvTime.text = dataBus.jam
        binding.tvTipe.text = dataBus.classBus
        binding.ivLogo.loadRoundedImage(dataBus.logo, 4)

        var showLabelKursi = ""
        for (i in dataKursi.indices) {
            showLabelKursi += "${dataKursi.get(i). nameKursi},"
        }
        binding.tvSeat.text = showLabelKursi.substring(0, showLabelKursi.length-1)
        binding.btnLanjutkan.setOnClickListener{
            listerner?.onOptionClick(dialog!!, dialogPosition)
        }
    }

    interface Listener {
        fun onOptionClick(dialog: Dialog, position: Int)
    }

    companion object {
        fun newInstance(listener: Listener, dataBusResponse: BusResponse, dataKursiParms : ArrayList<KursiResponse>) : ConfirmBottomSheet  {

            val instance = ConfirmBottomSheet()
            instance.listerner = listener
            instance.dataBus = dataBusResponse
            instance.dataKursi = dataKursiParms
            return instance

        }
    }

}