package com.example.asdadvance.ui.main.home.choice.bus


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.example.asdadvance.R
import com.example.asdadvance.databinding.ItemBusBinding
import com.example.asdadvance.databinding.ItemStringBinding
import com.example.asdadvance.databinding.ItemTerminalBinding
import com.example.asdadvance.databinding.ItemTimeBinding
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.TerminalModel
import com.example.asdadvance.model.response.BusResponse

class TimeAdapter (
    private val listData : ArrayList<String>, private val dataParms:String
    ) : RecyclerView.Adapter<TimeAdapter.ViewHolder>(){

    lateinit var binding : ItemTimeBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeAdapter.ViewHolder {
        binding = ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            itemView.apply {
                binding.tvItemString.text = data

                if (data.equals(dataParms)) {
                    binding.tvItemString.text = data
                    binding.tvItemString.setTextColor(resources.getColor(R.color.white))
                    binding.tvItemString.setBackgroundColor(R.drawable.sp_rectangle_radius_20_blue)
                }
            }
        }
    }

}