package com.viatom.blood.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viatom.blood.utils.DateStringUtil
import com.viatom.blood.MainApplication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [LPM311Data::class], version = 1)
abstract class LPM311AppDatabase : RoomDatabase() {
    abstract fun lpmDao(): LPM311Dao
    companion object{
        val lpmDb = Room.databaseBuilder(
            MainApplication.application,
            LPM311AppDatabase::class.java, "lpm-name"
        ).build()

        val dataScope = CoroutineScope(Dispatchers.IO)


        var lastSaveTime=0L;

        fun saveLPM(chol:Double,trig:Double,hdl:Double,ldl:Double,cholhdl:Double,time:Long){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime <3000){
                    return@launch
                }
                lastSaveTime =tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= LPM311Data();
                data.date=time;
                data.dateString=DateStringUtil.timeConvertEnglish(time);
                data.chol=chol
                data.trig=trig
                data.hdl=hdl
                data.ldl=ldl
                data.cholhdl=cholhdl
                lpmDb.lpmDao().insert(data)
            }
        }




        fun updateNote(date:Long,note:String){
            dataScope.launch {
                lpmDb.lpmDao().updateNote(note,date)
            }
        }
    }
}