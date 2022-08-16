package com.vaca.pc300.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeremyliao.liveeventbus.utils.AppUtils.init
import com.vaca.pc300.R


class PC300HistoryBpAdapter(var context: Context) :
    RecyclerView.Adapter<PC300HistoryBpAdapter.ViewHolder>() {

    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null

    var currentSelect = 0;


    private val mData: MutableList<String> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_pc300_history_bp, parent, false)
        return ViewHolder(view)
    }

    fun addAll(userBean: ArrayList<String>) {
        mData.clear()
        for (k in userBean) {
            mData.add(k)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val sys: TextView = itemView.findViewById(R.id.sys)
        val time:TextView = itemView.findViewById(R.id.time)
        val bpm:TextView = itemView.findViewById(R.id.bpm)

        init {

            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }


}