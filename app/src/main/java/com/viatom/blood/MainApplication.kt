package com.viatom.blood


import android.app.Application
import com.lepu.blepro.BleServiceHelper
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





    }


}