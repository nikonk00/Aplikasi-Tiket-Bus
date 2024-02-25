package com.example.asdadvance.ui.main.home.choice.tiket


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
import com.example.asdadvance.databinding.ItemBusBinding
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

class KursiAdapter (
    private val itemAdapterCallback : ItemListKursiAdapterCallBack
    ) : RecyclerView.Adapter<KursiAdapter.ViewHolder>(){

    private var listData = ArrayList<KursiResponse>()

    fun setData (listDataParms : ArrayList<KursiResponse>) {
        this.listData = listDataParms
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KursiAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == VIEW_TYPE_ITEM) {
            val view = layoutInflater.inflate(R.layout.item_kursi, parent, false)
            ViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item_kursi_label, parent, false)
            ViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM) {
            holder.bind(listData[position], itemAdapterCallback, position)
        } else {
            holder.bindLabel(listData[position])
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position%5==0) {
            return Const.RECYCLERVIEW_ITEM_KURSI.VIEW_TYPE_ITEM_LABEL
        } else {
            return VIEW_TYPE_ITEM
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: KursiResponse, itemAdapterCallback: ItemListKursiAdapterCallBack, position: Int) {

            itemView.apply {
                if (data.statusKursi == true) {
                    iv_kursi.setImageResource(R.drawable.ic_kursi_filled)
                } else {
                    itemView.iv_kursi.setOnClickListener {
                        if (data.checkKursi == true) {
                            iv_kursi.setImageResource(R.drawable.ic_kursi_empty)
                            itemAdapterCallback.onitemKursiAdapterCallBack(
                                it, data, false, position
                            )
                        } else {
                            iv_kursi.setImageResource(R.drawable.ic_kursi_check)
                            itemAdapterCallback.onitemKursiAdapterCallBack(
                                it, data, true, position
                            )
                        }
                    }
                }
            }

        }

        fun bindLabel(data:KursiResponse) {
            itemView.apply {
                tv_kursi.text = data.nameKursi
            }
        }
    }

    interface ItemListKursiAdapterCallBack {
        fun onitemKursiAdapterCallBack(v:View, data:KursiResponse, check:Boolean, position: Int)
    }

}