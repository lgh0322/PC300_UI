package com.vaca.pc300.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vaca.pc300.R
import com.vaca.pc300.room.PCdata


class PC300HistorySpo2Adapter(var context: Context) : RecyclerView.Adapter<PC300HistorySpo2Adapter.ViewHolder>() {


    var currentSelect=0;
    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null

    val mData: MutableList<PCdata> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_pc300_history_spo2, parent, false)
        return ViewHolder(view)
    }

    fun addAll(userBean: List<PCdata>) {
        mData.clear()
        for (k in userBean) {
            mData.add(k)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mData[position]
        holder.bpm.text=item.pr.toString()
        holder.o2.text=item.o2.toString()
        holder.time.text = item.dateString
    }


    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val o2:TextView=itemView.findViewById(R.id.spo2)
        val bpm:TextView=itemView.findViewById(R.id.bpm)
        val time:TextView=itemView.findViewById(R.id.time)
        init {

            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }





}