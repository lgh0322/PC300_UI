package com.viatom.blood


import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.ble.cmd.Pc300BleResponse
import com.lepu.blepro.event.InterfaceEvent
import com.vaca.pc300.room.PoctorAppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


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
                PoctorAppDatabase.saveBP(a.sys, a.dia, a.plus);
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300TempResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.TempResult
                PoctorAppDatabase.saveTemp(Math.round(a.temp * 10) / 10.0f)
            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300GluResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.GluResult
                PoctorAppDatabase.saveGlu(a.data)
            })


        var spo2Sum = 0;
        var prSum = 0;
        var prCount = 0;
        var prStartTime = 0L;
        var prEndTime = 0L;
        var prIsStart = false
        var wantSaveTime = 0L
        val handler = android.os.Handler(Looper.getMainLooper());
        var haveIn10 = false
        var duration=0;

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtOxyParam).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtOxyParam
                if (a.spo2 != 0 && a.pr != 0) {
                    if (!prIsStart) {
                        prIsStart = true
                        prStartTime = System.currentTimeMillis()
                        prCount = 1;
                        prSum = a.pr
                        spo2Sum = a.spo2
                    } else {
                        prCount++;
                        prSum += a.pr
                        spo2Sum += a.spo2
                    }
                } else {
                    prIsStart = false
                }

                if (a.isProbeOff) {
                    prEndTime = System.currentTimeMillis()
                    if (prEndTime - prStartTime > 3000) {
                        duration= ((prEndTime - prStartTime)/1000).toInt();
                        wantSaveTime = System.currentTimeMillis()
                        //保存数据。
                        haveIn10 = false
                        handler.postDelayed({
                            if (haveIn10 == false) {
                                Log.e("plpl","save")
                                PoctorAppDatabase.saveO2(spo2Sum/prCount, prSum/prCount,duration)
                            }
                        }, 10000)
                    }
                }else{
                    haveIn10 = true
                }
                Log.e("plpl", "gagaxxxxaaaaaa  " + a.spo2 + "  " + a.pr + "   " + a.isProbeOff)
            })



        val ecgList=ArrayList<Double>()


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtEcgWave).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtEcgWave
                if(a.seqNo==0){
                    ecgList.clear()
                }else{
                    for(k in a.wFs){
                        ecgList.add(k.toDouble())
                    }
                }
            })




        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300EcgResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.EcgResult
                Log.e("plpl", "gagaxxxxaaaaaa  " + a.resultMess+"   "+a.hr)
                val doubleArray=DoubleArray(ecgList.size){
                    ecgList[it]
                }
                PoctorAppDatabase.saveEcg(doubleArray,a.hr,a.resultMess)
            })
    }


}