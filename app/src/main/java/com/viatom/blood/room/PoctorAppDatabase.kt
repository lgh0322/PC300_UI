package com.vaca.pc300.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaca.pc300.utils.DateStringUtil
import com.viatom.blood.MainApplication

import com.viatom.blood.room.PoctorDao
import com.viatom.blood.room.PoctorData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [PoctorData::class], version = 1)
abstract class PoctorAppDatabase : RoomDatabase() {
    abstract fun pcDao(): PoctorDao
    companion object{
        val poctorDb = Room.databaseBuilder(
            MainApplication.application,
            PoctorAppDatabase::class.java, "poctor-name"
        ).build()

        val dataScope = CoroutineScope(Dispatchers.IO)

        const val TYPE_POCTOR_GLU=0;
        const val TYPE_POCTOR_KETONE=1;
        const val TYPE_POCTOR_URIC=2;

        var lastSaveTime=0L;

        fun savePoctorGlu(glu:Float){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime<3000){
                    return@launch
                }
                lastSaveTime=tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= PoctorData();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_POCTOR_GLU;
                data.glu=glu;
                poctorDb.pcDao().insert(data)
            }
        }

        fun savePoctorKetone(ketone:Float){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime<300){
                    return@launch
                }
                lastSaveTime=tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= PoctorData();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_POCTOR_KETONE;
                data.ketone=ketone
                poctorDb.pcDao().insert(data)
            }
        }

        fun savePoctorUric(uric:Float){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime<300){
                    return@launch
                }
                lastSaveTime=tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= PoctorData();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_POCTOR_URIC;
                data.uric=uric
                poctorDb.pcDao().insert(data)
            }
        }


        fun updateNote(date:Long,note:String){
            dataScope.launch {
                poctorDb.pcDao().updateNote(note,date)
            }
        }
    }
}