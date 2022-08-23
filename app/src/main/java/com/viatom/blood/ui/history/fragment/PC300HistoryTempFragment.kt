package com.viatom.blood.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viatom.blood.PC300TempDetailActivity
import com.viatom.blood.databinding.FragmentHistoryTempBinding
import com.viatom.blood.ui.history.adapter.PC300HistoryTempAdapter

class PC300HistoryTempFragment : Fragment() {

    private var _binding: FragmentHistoryTempBinding? = null


    private val binding get() = _binding!!

    private lateinit var leftAdapter: PC300HistoryTempAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryTempBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter= PC300HistoryTempAdapter(requireContext())

        binding.listView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.listView.adapter =leftAdapter


        leftAdapter.click=object : PC300HistoryTempAdapter.Click{
            override fun clickItem(position: Int) {
                startActivity(Intent(requireActivity(), PC300TempDetailActivity::class.java))
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}