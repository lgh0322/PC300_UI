package com.viatom.blood.ui.dashboard

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.listener.OnPageChangeListener
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.BleServiceHelper
import com.lepu.blepro.event.EventMsgConst
import com.lepu.blepro.objs.Bluetooth
import com.viatom.blood.MainApplication
import com.viatom.blood.R
import com.viatom.blood.databinding.Lpm311FragmentDashboardBinding
import com.viatom.blood.ui.dashboard.adapter.*
import androidx.lifecycle.Observer
import com.lepu.blepro.ble.data.Lpm311Data
import com.lepu.blepro.event.InterfaceEvent
import com.lepu.blepro.observer.BleChangeObserver
import com.viatom.blood.BleServer.dataScope
import com.viatom.blood.NetCmd
import com.viatom.blood.net.SimpleHttpServer
import com.viatom.blood.room.LPM311AppDatabase
import com.viatom.blood.utils.DateStringUtil
import com.viatom.blood.utils.PathUtil
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class LPM311DashboardFragment : Fragment() {

    private var _binding: Lpm311FragmentDashboardBinding? = null


    private val binding get() = _binding!!
    private lateinit var dataAdapter: LPMRealTimeDataAdapter

    val client = OkHttpClient();
    val url ="http://192.168.6.114"
    @Throws(IOException::class)
    fun uploadInfo(): ByteArray? {

        val request: Request = Request.Builder().url(url).build()
        client.newCall(request)
            .execute()
            .use { response ->
                {
                    if(response.code==200){
                        var inputStream :InputStream?=null;
                        try {
                            inputStream= response.body!!.byteStream()
                        }catch (e:Exception){

                        }finally {
                            inputStream?.close()
                        }
                    }
                }


            }
        return null
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = Lpm311FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        dataAdapter= LPMRealTimeDataAdapter(requireContext())


        dataScope.launch {
            val httpsServer = SimpleHttpServer()
            httpsServer.Start(13207)
        }

        binding.getImg.setOnClickListener {
            dataScope.launch {
                NetCmd.getFile(url,"ga.jpg",object:NetCmd.OnDownloadListener{
                    override fun onDownloadStart() {

                    }

                    override fun onDownloadSuccess(filePath: String?) {
                        val gg= File( PathUtil.getPathX("ga.jpg")).readBytes()
                        gg.let {
                           MainScope().launch {
                                val a= BitmapFactory.decodeStream(ByteArrayInputStream(it))
                                binding.img.setImageBitmap(a)
                            }
                        }
                    }

                    override fun onDownloadSuccessBytes(byteArray: ByteArray?) {

                    }

                    override fun onDownloading(progress: Int) {
                        Log.e("gaga",progress.toString())
                    }

                    override fun onDownloadFailed() {

                    }

                })

            }
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}