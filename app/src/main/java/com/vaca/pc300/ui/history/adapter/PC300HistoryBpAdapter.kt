package com.vaca.pc300.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.jeremyliao.liveeventbus.utils.AppUtils.init
import com.vaca.pc300.R
import com.vaca.pc300.room.PCdata
import com.vaca.pc300.ui.history.utils.BeBpDiagnosis


class PC300HistoryBpAdapter(var context: Context) :
    RecyclerView.Adapter<PC300HistoryBpAdapter.ViewHolder>() {

    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null

    var currentSelect = 0;


    private val mData: MutableList<PCdata> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_pc300_history_bp, parent, false)
        return ViewHolder(view)
    }

    fun addAll(userBean:List<PCdata>) {
        mData.clear()
        for (k in userBean) {
            mData.add(k)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mData[position]
        holder.sys.text=item.sys.toString()+"/"+item.dia.toString()
        holder.time.text=item.dateString
        holder.bpm.text=item.pr.toString()
        holder.healthIndex.background.level = BeBpDiagnosis(item.sys, item.dia).indicator

    }


    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val sys: TextView = itemView.findViewById(R.id.sys)
        val time:TextView = itemView.findViewById(R.id.time)
        val bpm:TextView = itemView.findViewById(R.id.bpm)

        val healthIndex: ImageView = itemView.findViewById(R.id.be_bp_mark)
        init {

            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }


}