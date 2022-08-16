package com.vaca.pc300.ui.dashboard

import android.os.Bundle
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
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dataAdapter: PC300DataDetailAdapter


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


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}