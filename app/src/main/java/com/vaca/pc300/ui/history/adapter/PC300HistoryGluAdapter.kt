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


class PC300HistoryGluAdapter(var context: Context) : RecyclerView.Adapter<PC300HistoryGluAdapter.ViewHolder>() {


        var currentSelect=0;
    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null

    private val mData: MutableList<PCdata> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("BP","SpO₂","Temp","GLU","ECG")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_pc300_history_glu, parent, false)
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
        val item = mData[position]
        holder.glu.text = item.glu.toString()
        holder.time.text = item.dateString
    }


    override fun getItemCount(): Int {
        return 25
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val glu:TextView=itemView.findViewById(R.id.glu)
        val time: TextView = itemView.findViewById(R.id.time)

        init {

            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }





}