package com.viatom.blood.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viatom.blood.R
import com.viatom.blood.room.PoctorData


class PoctorHistoryAdapter(var context: Context) :
    RecyclerView.Adapter<PoctorHistoryAdapter.ViewHolder>() {



    interface Click {
        fun clickItem(position: Int)
    }

    var click: Click? = null

    private val mData: MutableList<PoctorData> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_poctor_history, parent, false)
        return ViewHolder(view)
    }

    fun addAll(userBean: List<PoctorData>) {
        mData.clear()
        for (k in userBean) {
            mData.add(k)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mData[position]
        holder.time.text=item.dateString
        holder.data.text=item.value.toString()
    }


    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val data: TextView =itemView.findViewById(R.id.data)
        val time: TextView =itemView.findViewById(R.id.time)
        val status: TextView =itemView.findViewById(R.id.state)
        init {

            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }


}