package com.vaca.pc300.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lepu.blepro.event.EventMsgConst
import com.lepu.blepro.objs.Bluetooth
import com.vaca.pc300.databinding.FragmentDashboardBinding
import com.vaca.pc300.ui.dashboard.adapter.PC300DataDetailAdapter
import com.vaca.pc300.ui.dashboard.adapter.PC300ItemDecoration3
import androidx.lifecycle.Observer
import com.lepu.blepro.ble.cmd.Pc300BleResponse
import com.lepu.blepro.event.InterfaceEvent
import com.vaca.pc300.view.Er2Draw
import com.vaca.pc300.view.WaveView
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null


    private val binding get() = _binding!!
    private lateinit var dataAdapter: PC300DataDetailAdapter
    var drawTask: WaveView.Companion.DrawTask? = null

    var n=0;
    inner class RecTask() : TimerTask() {
        override fun run() {
            Log.e("dadahh",n.toString())
        }
    }

    var recTask: RecTask? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.bleState
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        dashboardViewModel.changeText("Divice is offline")
        dataAdapter= PC300DataDetailAdapter(requireContext())


        binding.pc300DataView.layoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.pc300DataView.adapter =dataAdapter
        binding.pc300DataView.addItemDecoration(PC300ItemDecoration3(30))

        LiveEventBus.get<Any>(EventMsgConst.Ble.EventBleDeviceReady).observe(this,
            Observer { o ->
                val a=o as Int
                if(a== Bluetooth.MODEL_PC300){
                    binding.bleState.visibility=View.GONE
                }
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtOxyParam).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.RtOxyParam
                dataAdapter.changeSpo2(a.spo2)
                dataAdapter.changePr(a.pr)
                Log.e("EventEr1RtData", "gagaxxxxaaaaaa  " + a.spo2 + "  " + a.pr)
            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300TempResult).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.TempResult
                dataAdapter.changeTemp(Math.round(a.temp*10)/10.0f)
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300GluResult).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.GluResult
                dataAdapter.changeGlu(a.data)
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300BpResult).observe(this,
            Observer { o ->
                val a = o.data as Pc300BleResponse.BpResult
                dataAdapter.changeSys(a.sys)
                dataAdapter.changeDia(a.dia)
                dataAdapter.changePr(a.plus)
            })


        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300EcgResult).observe(this,
            Observer { o ->

                val a = o.data as Pc300BleResponse.EcgResult
                binding.hr.text=a.hr.toString()
            })

        LiveEventBus.get<InterfaceEvent>(InterfaceEvent.PC300.EventPc300RtEcgWave).observe(this,
            Observer { o ->
                binding.hr.text="--"
                WaveView.disp=true
                val a = o.data as Pc300BleResponse.RtEcgWave
                for(k in a.wFs){
                    WaveView.waveDataX.offer(k)
                }
                n+=a.wFs.size
            })

        WaveView.reset()
        if (drawTask == null) {
            drawTask = WaveView.Companion.DrawTask()
            Timer().schedule(drawTask, Date(), 40)
        }

//        if (recTask == null) {
//            recTask =RecTask()
//            Timer().schedule(recTask, Date(), 1000)
//        }

        WaveView.er2Graph.observe(viewLifecycleOwner) {
            Log.e("gaga","gagax")
            binding.waveView.invalidate()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}