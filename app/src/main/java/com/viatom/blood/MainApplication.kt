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
import com.viatom.blood.utils.PathUtil

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
        PathUtil.initVar(this)

    }


}