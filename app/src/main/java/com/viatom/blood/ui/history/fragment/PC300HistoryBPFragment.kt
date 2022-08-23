package com.viatom.blood.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viatom.blood.PC300BPDetailActivity
import com.viatom.blood.databinding.FragmentHistoryBpBinding
import com.viatom.blood.ui.history.adapter.PC300HistoryBpAdapter

class PC300HistoryBPFragment : Fragment() {

    private var _binding: FragmentHistoryBpBinding? = null


    private val binding get() = _binding!!

    private lateinit var leftAdapter: PC300HistoryBpAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter= PC300HistoryBpAdapter(requireContext())

        binding.listView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.listView.adapter =leftAdapter

        leftAdapter.click=object :PC300HistoryBpAdapter.Click{
            override fun clickItem(position: Int) {
                startActivity(Intent(requireActivity(),PC300BPDetailActivity::class.java))
            }
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}