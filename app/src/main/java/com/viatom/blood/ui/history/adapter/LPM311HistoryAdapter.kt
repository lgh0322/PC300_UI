package com.viatom.blood.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viatom.blood.R
import com.viatom.blood.room.LPM311Data
import com.viatom.blood.ui.history.LPM311HistoryFragment
import com.viatom.blood.utils.PathUtil
import java.io.File


class LPM311HistoryAdapter(var context: Context) :
    RecyclerView.Adapter<LPM311HistoryAdapter.ViewHolder>() {


    var currentSelect = 0;

    interface Click {
        fun clickItem(position: Int)
    }

    var click: Click? = null

    val mData: MutableList<LPM311Data> = ArrayList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val name = listOf<String>("BP", "SpO₂", "Temp", "GLU", "ECG")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_lpm311_history, parent, false)
        return ViewHolder(view)
    }

    fun addAll(userBean: List<LPM311Data>) {
        mData.clear()
        for (k in userBean) {
            mData.add(k)
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mData[position]
        holder.time.text=item.dateString
        holder.status.setTag(position)


        LPM311HistoryFragment.currentSelect.postValue(item)
        val file= File(PathUtil.getPathX(item.name))
        if(file.exists()){
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
            Glide.with(context)
                .load(file)
                .apply(requestOptions)
                .into(holder.img)
            holder.img.visibility=View.VISIBLE
            holder.status.visibility=View.GONE
        }else{
            holder.img.visibility=View.INVISIBLE
            holder.status.text="未下载"
            holder.status.visibility=View.VISIBLE
        }
    }


    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val img:ImageView=itemView.findViewById(R.id.img)
        val time:TextView=itemView.findViewById(R.id.time)
        val status:TextView=itemView.findViewById(R.id.status)
        init {
            itemView.setTag(this)
            itemView.setOnClickListener {
                click?.clickItem(layoutPosition)
            }
        }
    }


}