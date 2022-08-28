package com.viatom.blood

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.text.format.Formatter.formatIpAddress
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.viatom.blood.databinding.Lpm311ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class LPM311MainActivity : AppCompatActivity() {
    private val networkManager by lazy {
        getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    private lateinit var binding: Lpm311ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Lpm311ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList=null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)





        val builder = NetworkRequest.Builder()
        val request = builder.build()
        networkManager.registerNetworkCallback(request, networkCallback)



    }


    private fun getWifiRouteIPAddress(context: Context): String {
        val wifi_service = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val dhcpInfo = wifi_service.dhcpInfo
        val routeIp: String = Formatter.formatIpAddress(dhcpInfo.gateway)
        return routeIp
    }


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            val a=getWifiRouteIPAddress(MainApplication.application);
            Log.e("gaga",a)
            MainScope().launch {
                BleServer.wifi.postValue(true)
            }

        }

        override fun onLost(network: Network) {
            super.onLost(network)
            MainScope().launch {
                BleServer.wifi.postValue(false)
            }

        }

    }




    override fun onDestroy() {
        networkManager.unregisterNetworkCallback(networkCallback)
        super.onDestroy()

    }
}