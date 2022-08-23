package com.viatom.blood


import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.ble.cmd.Pc300BleResponse
import com.lepu.blepro.ble.data.Lpm311Data
import com.lepu.blepro.event.EventMsgConst
import com.lepu.blepro.event.InterfaceEvent
import com.lepu.blepro.objs.Bluetooth
import com.viatom.blood.room.LPM311AppDatabase.Companion.saveLPM
import com.viatom.blood.utils.DateStringUtil

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainApplication : Application() {

    companion object {
        val dataScope = CoroutineScope(Dispatchers.IO)
        lateinit var application: Application
    }


    override fun onCreate() {
        super.onCreate()

        application = this
        BleServiceHelper.BleServiceHelper.initService(this, null)


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.LPM311.EventLpm311Data).observeForever(
            Observer { o ->
                val a = o.data as Lpm311Data
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.day)
                val date=DateStringUtil.getDate(a.year,a.month,a.day,a.hour,a.minute,a.second)
                val cc= date.time
                val ts = DateStringUtil.timeConvertEnglish(cc)
                Log.e("gagax",ts)
                saveLPM(a.chol,a.trig,a.hdl,a.ldl,a.cholDivHdl,cc)
            })


        LiveEventBus.get<Any>(EventMsgConst.Ble.EventBleDeviceReady).observeForever(
            Observer { o ->
                val a=o as Int
                if(a== Bluetooth.MODEL_LPM311){
                    dataScope.launch {
                        delay(5000)
                        BleServiceHelper.BleServiceHelper.getFileList(Bluetooth.MODEL_LPM311,null)
                    }

                }
            })



    }


}