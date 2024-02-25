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
import com.example.asdadvance.databinding.BottomSheetListTerminalBinding
import com.example.asdadvance.model.TerminalModel
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomPenumpangAdapter
import com.example.asdadvance.ui.dialog.bottomsheet.adapter.ListBottomTerminalAdapter
import java.text.FieldPosition

class ListPenumpangBottomSheet : BaseBindingBottomSheet() , ListBottomPenumpangAdapter.ItemAdapterCallback{

    private lateinit var binding : BottomSheetListTerminalBinding
    private var dialogPosition : Int = 0
    private var listerner : Listener ?= null
    private lateinit var data : ArrayList<Int>
    private lateinit var title : String
    private lateinit var subtitle : String

    override fun getFragmentView(): ViewBinding {
        binding = BottomSheetListTerminalBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.tvTitle.text = title
        binding.tvSubtitle.text = subtitle

        var adapter = ListBottomPenumpangAdapter(data, this)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvListString.layoutManager = layoutManager
        binding.rvListString.adapter = adapter
        binding.rvListString.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onListBottomTerminalClick(v: View, data: Int) {
        dialog?.dismiss()
        listerner?.onClick(data)
    }

    interface Listener {
        fun onClick(data: Int)
    }

    companion object {
        fun newInstance(listener: Listener, position: Int, data : ArrayList<Int>, title:String, subtitle:String) : ListPenumpangBottomSheet {

            val instance = ListPenumpangBottomSheet()
            instance.listerner = listener
            instance.dialogPosition = position
            instance.data = data
            instance.title = title
            instance.subtitle = subtitle
            return instance

        }
    }

}