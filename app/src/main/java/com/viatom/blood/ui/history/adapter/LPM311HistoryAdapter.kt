package com.viatom.blood.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lepu.blepro.event.InterfaceEvent
import com.viatom.blood.R
import com.viatom.blood.room.LPM311Data


class LPM311HistoryAdapter(var context: Context) :
    RecyclerView.Adapter<LPM311HistoryAdapter.ViewHolder>() {


    var currentSelect = 0;

    interface Click {
        fun clickItem(position: Int)
    }

    var click: Click? = null

    val mData: MutableList<LPM311Data> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name = listOf<String>("BP", "SpO₂", "Temp", "GLU", "ECG")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_lpm311_history, parent, false)
        return ViewHolder(view)
    }

    fun addAll(userBean: List<LPM311Data>) {
        mData.clear()
        for (k in userBean) {
            mData.add(k)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mData[position]
        holder.time.text=item.dateString
    }


    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val img:ImageView=itemView.findViewById(R.id.img)
        val time:TextView=itemView.findViewById(R.id.time)
        init {
            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }


}