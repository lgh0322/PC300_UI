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
        val pc300db = Room.databaseBuilder(
            MainApplication.application,
            PoctorAppDatabase::class.java, "pc300-name"
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
                val data= PoctorData();
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
                data.type= TYPE_TEMP;
                data.temp=temp;
                pc300db.pcDao().insert(data)

            }
        }

        fun saveEcg(wave:DoubleArray,hr:Int,result:String){
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
                data.type= TYPE_ECG;
                data.hr=hr;
                data.ecg_result=result;
                pc300db.pcDao().insert(data)

            }
        }


        fun saveGlu(glu:Float){
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
                data.type= TYPE_GLU;
                data.glu=glu;
                pc300db.pcDao().insert(data)

            }
        }


        fun saveO2(o2:Int,pr:Int,duration:Int){
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
                data.type= TYPE_O2;
                data.o2= o2;
                data.pr=pr;
                data.o2_duration=duration;
                pc300db.pcDao().insert(data)

            }
        }

        fun updateNote(date:Long,note:String){
            dataScope.launch {
                pc300db.pcDao().updateNote(note,date)
            }
        }
    }
}