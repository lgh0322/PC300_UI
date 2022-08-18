package com.vaca.pc300.room

import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaca.pc300.MainApplication
import com.vaca.pc300.utils.DateStringUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [PCdata::class], version = 1)
abstract class PcAppDatabase : RoomDatabase() {
    abstract fun pcDao(): PCDao
    companion object{
        val pc300db = Room.databaseBuilder(
            MainApplication.application,
            PcAppDatabase::class.java, "pc300-name"
        ).build()

        val dataScope = CoroutineScope(Dispatchers.IO)

        const val TYPE_BP=0;
        const val TYPE_ECG=1;
        const val TYPE_TEMP=2;
        const val TYPE_O2=3;
        const val TYPE_GLU=4;

        var lastSaveTime=0L;

        fun saveBP(sys:Int,dia:Int,pulse:Int){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime<3000){
                    return@launch
                }
                lastSaveTime=tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data=PCdata();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_BP;
                data.sys=sys;
                data.dia=dia;
                data.pr=pulse;
                pc300db.pcDao().insert(data)

            }
        }

        fun saveTemp(temp:Float){

        }

        fun saveEcg(wave:DoubleArray,hr:Int,result:String){

        }


        fun saveGlu(glu:Float){

        }


        fun saveO2(o2:Int,pr:Int){

        }
    }
}