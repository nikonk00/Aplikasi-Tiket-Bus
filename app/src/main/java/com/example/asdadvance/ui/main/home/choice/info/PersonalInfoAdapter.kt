package com.example.asdadvance.ui.main.home.choice.info


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.asdadvance.utils.Const
import com.bumptech.glide.load.engine.Resource
import com.example.asdadvance.R
import com.example.asdadvance.databinding.FragmentHomeBinding.inflate
import com.example.asdadvance.databinding.FragmentPersonalInfoBinding
import com.example.asdadvance.databinding.ItemBusBinding
import com.example.asdadvance.databinding.ItemPenumpangBinding
import com.example.asdadvance.databinding.ItemStringBinding
import com.example.asdadvance.databinding.ItemTerminalBinding
import com.example.asdadvance.databinding.ItemTimeBinding
import com.example.asdadvance.model.BusRequest
import com.example.asdadvance.model.TerminalModel
import com.example.asdadvance.model.response.BusResponse
import com.example.asdadvance.model.response.KursiResponse
import com.example.asdadvance.ui.main.home.choice.bus.PilihBusAdapter
import com.example.asdadvance.utils.Const.RECYCLERVIEW_ITEM_KURSI.VIEW_TYPE_ITEM
import kotlinx.android.synthetic.main.item_kursi.view.*
import kotlinx.android.synthetic.main.item_kursi_label.view.*
import kotlin.reflect.typeOf

class PersonalInfoAdapter (
    private val listData: ArrayList<String>,
    private val itemAdapterCallback : ItemListPenumpangAdapterCallBack
) : RecyclerView.Adapter <PersonalInfoAdapter.ViewHolder>() {

    lateinit var binding : ItemPenumpangBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonalInfoAdapter.ViewHolder {

        binding = ItemPenumpangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback, position)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding: ItemPenumpangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String, itemAdapterCallback: ItemListPenumpangAdapterCallBack, position: Int) {

            itemView.apply {
                binding.tvName.text = data
                binding.ivName.setOnClickListener {
                    itemAdapterCallback.onitemPenumpangAdapterCallBack(data,position)
                }
            }

        }
    }

    interface ItemListPenumpangAdapterCallBack {
        fun onitemPenumpangAdapterCallBack(data:String, position: Int)
    }

}