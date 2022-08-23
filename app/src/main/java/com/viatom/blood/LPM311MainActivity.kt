package com.viatom.blood

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.base.LpWorkManager
import com.lepu.blepro.event.EventMsgConst
import com.lepu.blepro.objs.Bluetooth
import com.lepu.blepro.utils.LepuBleLog
import com.viatom.blood.databinding.Lpm311ActivityMainBinding
import kotlinx.coroutines.launch

class LPM311MainActivity : AppCompatActivity() {

    private lateinit var binding: Lpm311ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Lpm311ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList=null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        bleInit()




    }


    private fun bleInit() {
        BleServiceHelper.BleServiceHelper.startScan(Bluetooth.MODEL_LPM311, false)
        LepuBleLog.setDebug(true)
        BleServiceHelper.BleServiceHelper.setInterfaces(Bluetooth.MODEL_LPM311, true);
        LiveEventBus.get<Bluetooth>(EventMsgConst.Discovery.EventDeviceFound).observe(this) { res ->

                BleServiceHelper.BleServiceHelper.stopScan();
                BleServiceHelper.BleServiceHelper.connect(
                    MainApplication.application,
                    Bluetooth.MODEL_LPM311,
                    res.device,
                    true,
                    LpWorkManager.toConnectUpdater
                )

        }
    }

}