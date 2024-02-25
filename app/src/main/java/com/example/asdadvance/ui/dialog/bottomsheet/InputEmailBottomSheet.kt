package com.example.asdadvance.ui.dialog.bottomsheet

import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingBottomSheet
import com.example.asdadvance.R
import com.example.asdadvance.databinding.BottomSheetInputEmailBinding
import com.example.asdadvance.databinding.BottomSheetListTerminalBinding
import com.example.asdadvance.model.TerminalModel
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomPenumpangAdapter
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomTerminalAdapter
import java.text.FieldPosition

class InputEmailBottomSheet : BaseBindingBottomSheet() {

    private lateinit var binding : BottomSheetInputEmailBinding
    private var listerner : Listener ?= null
    private lateinit var title : String
    private lateinit var subtitle : String
    private var valueParms : String?=""

    override fun getFragmentView(): ViewBinding {
        binding = BottomSheetInputEmailBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.tvTitle.text = title
        binding.tvSubtitle.text = subtitle

        if (!valueParms.isNullOrEmpty()) {
            binding.etInputEmail.setText(valueParms)
            binding.btnSimpan.setOnClickListener{
                var valueString = binding.etInputEmail.text.toString()
                if (valueString.isNullOrEmpty()) {
                    binding.etInputEmail.error = "Silahkan isi nama email"
                } else {
                    dialog!!.dismiss()
                    listerner?.onClick(valueString)
                }
            }
        }
    }

    interface Listener {
        fun onClick(data: String)
    }

    companion object {
        fun newInstance(listener: Listener, title:String, subtitle:String, valueParms : String?="") : InputEmailBottomSheet {

            val instance = InputEmailBottomSheet()
            instance.listerner = listener
            instance.valueParms = valueParms
            instance.title = title
            instance.subtitle = subtitle
            return instance

        }
    }

}