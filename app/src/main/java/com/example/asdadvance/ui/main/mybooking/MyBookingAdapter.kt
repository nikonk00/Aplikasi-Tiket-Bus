package com.example.asdadvance.ui.main.mybooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.bagicodebaseutils.utils.formatPrice
import com.example.asdadvance.databinding.ItemBusBinding
import com.example.asdadvance.databinding.ItemMybookingBinding
import com.example.asdadvance.databinding.ItemStringBinding
import com.example.asdadvance.databinding.ItemTerminalBinding
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.TerminalModel
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.MyBookingResponse

class MyBookingAdapter (
    private val listData : ArrayList<MyBookingResponse>,
    private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter <MyBookingAdapter.ViewHolder>() {

    lateinit var binding : ItemMybookingBinding

    interface ItemAdapterCallback {
        fun onListMyBookingClick(v : View, data:MyBookingResponse)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyBookingAdapter.ViewHolder {
        binding = ItemMybookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemMybookingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyBookingResponse, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvBookingId.text = data.idTiket
                binding.tvCodeFrom.text = data.codeTerminalDari
                binding.tvCodeTo.text = data.codeTerminalTujuan
                binding.tvDate.text = data.dateKeberangkatan

                data.totalPrice?.let { binding.tvPrice.formatPrice(it) }

                itemView.setOnClickListener {
                    itemAdapterCallback.onListMyBookingClick(it, data)
                }
            }
        }
    }
}