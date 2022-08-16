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
    val value= arrayListOf<String>("","","","","","","","")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.pc300_detail_data_layout, parent, false)
        return ViewHolder(view)
    }


    fun changeSys(sys:Int){
        if(sys==0){
            return
        }
        value[0]=sys.toString()
        notifyItemChanged(0)
    }

    fun changeDia(dia:Int){
        if(dia==0){
            return
        }
        value[1]=dia.toString()
        notifyItemChanged(1)
    }

    fun changeSpo2(spo2:Int){
        if(spo2==0){
            return
        }
        value[2]=spo2.toString()
        notifyItemChanged(2)
    }

    fun changePr(pr:Int){
        if(pr==0){
            return
        }
        value[3]=pr.toString()
        notifyItemChanged(3)
    }

    fun changeTemp(temp:Float){
        value[4]=temp.toString()
        notifyItemChanged(4)
    }

    fun changeGlu(glu:Float){
        value[5]=glu.toString()
        notifyItemChanged(5)
    }

    fun changeUa(ua:Double){
        value[6]=ua.toString()
        notifyItemChanged(6)
    }

    fun changeChol(chol:Int){
        value[7]=chol.toString()
        notifyItemChanged(7)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=name[position]
        holder.unit.text=unit[position]
        if(value[position].isEmpty()){
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