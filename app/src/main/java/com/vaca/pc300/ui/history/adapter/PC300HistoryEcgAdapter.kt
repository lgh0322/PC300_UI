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


class PC300HistoryEcgAdapter(var context: Context) : RecyclerView.Adapter<PC300HistoryEcgAdapter.ViewHolder>() {


    var currentSelect=0;
    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null

   val mData: MutableList<PCdata> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_pc300_history_ecg, parent, false)
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
       // holder.sys.text=name[position]
        if(position== currentSelect){
            //holder.sys.setTextColor(ContextCompat.getColor(context,R.color.color_111827))

        }else{
           // holder.sys.setTextColor(ContextCompat.getColor(context,R.color.color_9CA3AF))

        }
    }


    override fun getItemCount(): Int {
        return 25
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val sys:TextView=itemView.findViewById(R.id.sys)

        init {

            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }





}