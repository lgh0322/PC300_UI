package com.vaca.pc300


import android.app.Application
import com.lepu.blepro.BleServiceHelper


class MainApplication : Application() {

    companion object {
        lateinit var application: Application
    }


    override fun onCreate() {
        super.onCreate()

        application = this
        BleServiceHelper.BleServiceHelper.initService(this, null)
    }


}