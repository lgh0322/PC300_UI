package com.vaca.pc300

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.base.LpWorkManager
import com.lepu.blepro.ble.cmd.Pc300BleResponse
import com.lepu.blepro.event.EventMsgConst
import com.lepu.blepro.event.InterfaceEvent
import com.lepu.blepro.objs.Bluetooth
import com.lepu.blepro.objs.Bluetooth.MODEL_PC300
import com.lepu.blepro.observer.BleChangeObserver
import com.lepu.blepro.utils.LepuBleLog
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.room.PCdata
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.utils.DateStringUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),BleChangeObserver {
    val dataScope = CoroutineScope(Dispatchers.IO)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        bleInit()
        initDataObserve()



        val tsMother = System.currentTimeMillis()
        val ts = DateStringUtil.timeConvertEnglish(tsMother)
        Log.e("gagaxx",ts)
    }


    private fun bleInit() {
        BleServiceHelper.BleServiceHelper.startScan(Bluetooth.MODEL_PC300, false)
        LepuBleLog.setDebug(true)
        BleServiceHelper.BleServiceHelper.setInterfaces(Bluetooth.MODEL_PC300, true);
        LiveEventBus.get<Bluetooth>(EventMsgConst.Discovery.EventDeviceFound).observe(this) { res ->
            if (res.device.address == "88:1B:99:30:8D:45") {
                BleServiceHelper.BleServiceHelper.stopScan();
                BleServiceHelper.BleServiceHelper.connect(
                    MainApplication.application,
                    Bluetooth.MODEL_PC300,
                    res.device,
                    true,
                    LpWorkManager.toConnectUpdater
                )
            }
        }
    }


    private fun initDataObserve() {


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtBpData).observe(this,
            Observer { o ->
                val a = o.data as Int
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.toString())
            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300BpResult).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.BpResult
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.dia + "  " + a.sys + "   " + a.plus+"   ")
                val tsMother = System.currentTimeMillis()
                val ts = DateStringUtil.timeConvertEnglish(tsMother)
               /* val pcData=PCdata(tsMother,ts,"",a.sys,a.dia,a.plus,"")

                dataScope.launch {
                    PcAppDatabase.pc300db.pcDao().insert(pcData)
                }*/

            })



        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtEcgWave).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtEcgWave
                //Log.e("EventEr1RtData","gagaxxxxaaaaaa  "+a.dia+"  "+a.sys+"   "+a.plus)
            })




        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300EcgResult).observe(this,
            Observer { o ->

                val a = o.data as Pc300BleResponse.EcgResult
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.resultMess)
            })



        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300TempResult).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.TempResult
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.temp)
            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300GluResult).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.GluResult
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.resultMess + "  " + a.data)
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtOxyParam).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtOxyParam
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.spo2 + "  " + a.pr)
            })



        LiveEventBus.get<Any>(EventMsgConst.Ble.EventBleDeviceReady).observe(this,
            Observer { o ->
                val a=o as Int
                if(a==MODEL_PC300){

                }
            })
    }

    override fun onBleStateChanged(model: Int, state: Int) {
        TODO("Not yet implemented")
    }
}