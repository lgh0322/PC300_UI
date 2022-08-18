package com.vaca.pc300


import android.app.Application
import android.util.Log
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.ble.cmd.Pc300BleResponse
import com.lepu.blepro.event.InterfaceEvent
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


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtOxyParam).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtOxyParam
                Log.e("gagaggg",a.spo2.toString())

            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300BpResult).observeForever(
            Observer { o ->
                val a = o.data as Pc300BleResponse.BpResult

            })
    }


}