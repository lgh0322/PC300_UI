package com.viatom.blood.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viatom.blood.R


class LPMRealTimeDataAdapter(var context: Context) : RecyclerView.Adapter<LPMRealTimeDataAdapter.ViewHolder>() {

    private val mData: MutableList<String> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("CHOL","TRIG","HDL","LDL","CHOL/HDL","GLU","UA","CHOL")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_lpm311_real_time, parent, false)
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
        holder.name.text=name[position]
      //  holder.unit.text=unit[position]

    }


    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val value:TextView=itemView.findViewById(R.id.value)
        val name:TextView=itemView.findViewById(R.id.name)
    }





}