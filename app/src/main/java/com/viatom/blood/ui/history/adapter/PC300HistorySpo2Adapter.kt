package com.viatom.blood.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viatom.blood.R


class PC300HistorySpo2Adapter(var context: Context) : RecyclerView.Adapter<PC300HistorySpo2Adapter.ViewHolder>() {


        var currentSelect=0;
    interface Click{
        fun clickItem(position: Int)
    }

    var click:Click?=null

    private val mData: MutableList<String> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name= listOf<String>("BP","SpO₂","Temp","GLU","ECG")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_pc300_history_spo2, parent, false)
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