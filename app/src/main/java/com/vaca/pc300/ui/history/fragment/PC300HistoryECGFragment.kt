package com.vaca.pc300.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.ui.history.detail.PC300EcgDetailActivity
import com.vaca.pc300.databinding.FragmentHistoryEcgBinding
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.ui.history.HistoryFragment
import com.vaca.pc300.ui.history.adapter.PC300HistoryEcgAdapter

class PC300HistoryECGFragment : Fragment() {

    private var _binding: FragmentHistoryEcgBinding? = null


    private val binding get() = _binding!!

    private lateinit var leftAdapter: PC300HistoryEcgAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryEcgBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter= PC300HistoryEcgAdapter(requireContext())

        binding.listView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.listView.adapter =leftAdapter

        leftAdapter.click=object : PC300HistoryEcgAdapter.Click{
            override fun clickItem(position: Int) {
                HistoryFragment.currentSelect.postValue(leftAdapter.mData.get(position))
                startActivity(Intent(requireActivity(), PC300EcgDetailActivity::class.java))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}