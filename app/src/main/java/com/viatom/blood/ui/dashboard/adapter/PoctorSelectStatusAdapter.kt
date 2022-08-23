package com.viatom.blood.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.viatom.blood.R


class PoctorSelectStatusAdapter(var context: Context) : RecyclerView.Adapter<PoctorSelectStatusAdapter.ViewHolder>() {


    var currentSelect=0;
    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null


    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("Fasting","Before meals","After meals","Random")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_select_status, parent, false)
        return ViewHolder(view)
    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=name[position]
        if(position==currentSelect){
            holder.name.background=ContextCompat.getDrawable(context,R.drawable.poctor_text_bg)
            holder.name.setTextColor(ContextCompat.getColor(context,R.color.color_3784FB))
        }else{
            holder.name.background=ContextCompat.getDrawable(context,R.drawable.poctor_text_bg_unselect)
            holder.name.setTextColor(ContextCompat.getColor(context,R.color.color_9CA3AF))
        }
    }


    override fun getItemCount(): Int {
        return 4
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name:TextView= itemView.findViewById(R.id.name)
        init {

            itemView.setOnClickListener {
                currentSelect=layoutPosition
                notifyDataSetChanged()
            }
        }
    }





}