package com.viatom.blood

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object BleServer {

    val wifi = MutableLiveData<Boolean>()
    val dataScope = CoroutineScope(Dispatchers.IO)

}