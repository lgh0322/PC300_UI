package com.viatom.blood


import android.R.attr.port
import android.app.Application
import com.viatom.blood.net.SimpleHttpServer
import com.viatom.blood.utils.PathUtil
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
        PathUtil.initVar(this)



    }


}