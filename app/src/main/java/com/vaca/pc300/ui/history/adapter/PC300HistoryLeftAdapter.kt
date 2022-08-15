package com.vaca.pc300.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vaca.pc300.R


class PC300HistoryLeftAdapter(var context: Context) : RecyclerView.Adapter<PC300HistoryLeftAdapter.ViewHolder>() {

    companion object{
        var currentSelect=0;
    }

    private val mData: MutableList<String> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("BP","SpO₂","Temp","GLU","ECG")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.pc300_history_left_detail, parent, false)
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
        if(position== currentSelect){
            holder.name.setTextColor(ContextCompat.getColor(context,R.color.color_111827))
            holder.main.setBackgroundColor(ContextCompat.getColor(context,R.color.color_FCFCFC))
        }else{
            holder.name.setTextColor(ContextCompat.getColor(context,R.color.color_9CA3AF))
            holder.main.setBackgroundColor(ContextCompat.getColor(context,R.color.color_F3F4F6))
        }
    }


    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name:TextView=itemView.findViewById(R.id.name)
        val main:ConstraintLayout=itemView.findViewById(R.id.main)
        init {

            itemView.setOnClickListener {
                currentSelect=layoutPosition
                notifyDataSetChanged()
            }
        }
    }





}