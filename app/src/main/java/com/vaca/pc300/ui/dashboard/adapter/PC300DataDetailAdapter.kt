package com.vaca.pc300.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vaca.pc300.R


class PC300DataDetailAdapter(var context: Context) : RecyclerView.Adapter<PC300DataDetailAdapter.ViewHolder>() {

    private val mData: MutableList<String> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("SYS","DIA","SpO₂","PR","Temp","GLU","UA","CHOL")
    val unit= listOf<String>("mmHg","mmHg","%","bpm","°C","mmol/L","mg/dL","mg/dL")
    val value= listOf<Int>(-1,-1,-1,-1,-1,-1,-1,-1)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.pc300_detail_data_layout, parent, false)
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
        holder.unit.text=unit[position]
        if(value[position]==-1){
            holder.value.visibility=View.GONE
            holder.emptySign.visibility=View.VISIBLE
        }else{
            holder.value.visibility=View.VISIBLE
            holder.emptySign.visibility=View.GONE
            holder.value.text=value[position].toString()
        }
    }


    override fun getItemCount(): Int {
        return 8
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name)
        val unit:TextView=itemView.findViewById(R.id.unit)
        val value:TextView=itemView.findViewById(R.id.value)
        val emptySign:LinearLayout=itemView.findViewById(R.id.empty_sign)
    }





}