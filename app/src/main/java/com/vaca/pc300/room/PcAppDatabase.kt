package com.vaca.pc300.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaca.pc300.MainApplication

@Database(entities = [PCdata::class], version = 1)
abstract class PcAppDatabase : RoomDatabase() {
    abstract fun pcDao(): PCDao
    companion object{
        val pc300db = Room.databaseBuilder(
            MainApplication.application,
            PcAppDatabase::class.java, "pc300-name"
        ).build()


        const val TYPE_BP=0;
        const val TYPE_ECG=1;
        const val TYPE_TEMP=2;
        const val TYPE_O2=3;
        const val TYPE_GLU=4;



        fun saveBP(sys:Int,dia:Int,pulse:Int){

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