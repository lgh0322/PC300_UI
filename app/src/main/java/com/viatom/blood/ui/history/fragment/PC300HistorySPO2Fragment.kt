package com.viatom.blood.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viatom.blood.PC300Spo2DetailActivity
import com.viatom.blood.databinding.FragmentHistorySpo2Binding
import com.viatom.blood.ui.history.adapter.PC300HistorySpo2Adapter

class PC300HistorySPO2Fragment : Fragment() {

    private var _binding: FragmentHistorySpo2Binding? = null


    private val binding get() = _binding!!

    private lateinit var leftAdapter: PC300HistorySpo2Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistorySpo2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter= PC300HistorySpo2Adapter(requireContext())

        binding.listView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.listView.adapter =leftAdapter

        leftAdapter.click=object : PC300HistorySpo2Adapter.Click{
            override fun clickItem(position: Int) {
                startActivity(Intent(requireActivity(), PC300Spo2DetailActivity::class.java))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}