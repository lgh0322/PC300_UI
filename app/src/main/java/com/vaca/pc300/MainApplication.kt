package com.vaca.pc300


import android.app.Application
import android.util.Log
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.ble.cmd.Pc300BleResponse
import com.lepu.blepro.event.InterfaceEvent
import com.vaca.pc300.room.PcAppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.logging.Handler


class MainApplication : Application() {

    companion object {
        val dataScope = CoroutineScope(Dispatchers.IO)
        lateinit var application: Application
    }


    override fun onCreate() {
        super.onCreate()

        application = this
        BleServiceHelper.BleServiceHelper.initService(this, null)




        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300BpResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.BpResult
                PcAppDatabase.saveBP(a.sys,a.dia,a.plus);
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300TempResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.TempResult
                PcAppDatabase.saveTemp(Math.round(a.temp*10)/10.0f)
            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300GluResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.GluResult
                PcAppDatabase.saveGlu(a.data)
            })


        var spo2Sum=0;
        var prSum=0;
        var prCount=0;
        var prStartTime=0L;
        var prEndTime=0L;
        var prIsStart=false
        var wantSaveTime=0L
        val handler= android.os.Handler();

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtOxyParam).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtOxyParam
                if(a.spo2!=0 && a.pr!=0){
                    if(!prIsStart){
                        prIsStart=true
                        prStartTime=System.currentTimeMillis()
                        prCount=1;
                        prSum=a.pr
                        spo2Sum=a.spo2
                    }else{
                        prCount++;
                        prSum+=a.pr
                        spo2Sum+=a.spo2
                    }
                }else{
                    prIsStart=false
                }

                if(a.isProbeOff){
                    prEndTime=System.currentTimeMillis()
                    if(prEndTime-prStartTime>3000){
                        wantSaveTime=System.currentTimeMillis()
                        //保存数据。
                    }
                }
                Log.e("plpl", "gagaxxxxaaaaaa  " + a.spo2 + "  " + a.pr+"   "+a.isProbeOff)
            })
    }


}