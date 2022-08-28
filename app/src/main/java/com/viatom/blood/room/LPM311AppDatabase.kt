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

        fun saveLPM(name:String,len:Int,time:Long){
            dataScope.launch {
                val ts = DateStringUtil.timeConvertEnglish(time)
                val data= LPM311Data();
                data.date=time;
                data.dateString=DateStringUtil.timeConvertEnglish(time);
                data.name=name
                data.size=len
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