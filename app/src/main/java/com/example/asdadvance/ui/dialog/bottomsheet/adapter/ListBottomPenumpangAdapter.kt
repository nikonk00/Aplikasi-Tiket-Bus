package com.example.asdadvance.ui.dialog.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asdadvance.databinding.ItemStringBinding
import com.example.asdadvance.model.TerminalModel

class ListBottomPenumpangAdapter (
    private val listData : ArrayList<Int>,
    private val itemAdapterCallback : ItemAdapterCallback
        ) : RecyclerView.Adapter<ListBottomPenumpangAdapter.ViewHolder>(){

    lateinit var binding :ItemStringBinding

    interface ItemAdapterCallback {
        fun onListBottomTerminalClick(v : View, data:Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBottomPenumpangAdapter.ViewHolder {
        binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemStringBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Int, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvItemString.text = data.toString()
//                binding.tvItemSub.text = data.codeTerminal

                itemView.setOnClickListener {
                    itemAdapterCallback.onListBottomTerminalClick(it, data)
                }
            }
        }
    }

}