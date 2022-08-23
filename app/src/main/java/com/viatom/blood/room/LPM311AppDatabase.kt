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
    abstract fun pcDao(): LPM311Dao
    companion object{
        val poctorDb = Room.databaseBuilder(
            MainApplication.application,
            LPM311AppDatabase::class.java, "poctor-name"
        ).build()

        val dataScope = CoroutineScope(Dispatchers.IO)

        const val TYPE_POCTOR_GLU=0;
        const val TYPE_POCTOR_KETONE=1;
        const val TYPE_POCTOR_URIC=2;

        var lastSaveTime=0L;

        fun savePoctorGlu(glu:Float,state:Int=0){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime <3000){
                    return@launch
                }
                lastSaveTime =tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= LPM311Data();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_POCTOR_GLU;
                data.value=glu;
                poctorDb.pcDao().insert(data)
            }
        }

        fun savePoctorKetone(ketone:Float){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime <300){
                    return@launch
                }
                lastSaveTime =tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= LPM311Data();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_POCTOR_KETONE;
                data.value=ketone
                poctorDb.pcDao().insert(data)
            }
        }

        fun savePoctorUric(uric:Float){
            dataScope.launch {
                val tsMother = System.currentTimeMillis()
                if(tsMother- lastSaveTime <300){
                    return@launch
                }
                lastSaveTime =tsMother;
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
                val data= LPM311Data();
                data.date=tsMother;
                data.dateString=ts;
                data.type= TYPE_POCTOR_URIC;
                data.value=uric
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