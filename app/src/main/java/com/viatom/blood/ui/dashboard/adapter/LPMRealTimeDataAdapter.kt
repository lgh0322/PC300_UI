package com.viatom.blood.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.viatom.blood.R


class LPMRealTimeDataAdapter(var context: Context) : RecyclerView.Adapter<LPMRealTimeDataAdapter.ViewHolder>() {

    private val mData: MutableList<Double> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("CHOL","TRIG","HDL","LDL","CHOL/HDL","GLU","UA","CHOL")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_lpm311_real_time, parent, false)
        return ViewHolder(view)
    }

    fun addAll(chol:Double,trig:Double,hdl:Double,ldl:Double,cholhdl:Double) {
        mData.clear()
        mData.add(chol)
        mData.add(trig)
        mData.add(hdl)
        mData.add(ldl)
        mData.add(cholhdl)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(mData.size==5){
            holder.emptySign.visibility=View.GONE
            holder.value.visibility=View.VISIBLE

            holder.value.text=mData[position].toString()
        }else{
            holder.emptySign.visibility=View.VISIBLE
            holder.value.visibility=View.GONE
        }
        holder.name.text=name[position]
      //  holder.unit.text=unit[position]

    }


    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val value:TextView=itemView.findViewById(R.id.value)
        val unit:TextView=itemView.findViewById(R.id.unit)
        val name:TextView=itemView.findViewById(R.id.name)
        val emptySign:LinearLayout=itemView.findViewById(R.id.empty_sign)
    }





}