package com.vaca.pc300.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.ui.history.detail.PC300GluDetailActivity
import com.vaca.pc300.databinding.FragmentHistoryGluBinding
import com.vaca.pc300.ui.history.adapter.PC300HistoryGluAdapter

class PC300HistoryGluFragment : Fragment() {

    private var _binding: FragmentHistoryGluBinding? = null


    private val binding get() = _binding!!

    private lateinit var leftAdapter: PC300HistoryGluAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryGluBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter= PC300HistoryGluAdapter(requireContext())

        binding.listView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.listView.adapter =leftAdapter

        leftAdapter.click=object : PC300HistoryGluAdapter.Click{
            override fun clickItem(position: Int) {
                startActivity(Intent(requireActivity(), PC300GluDetailActivity::class.java))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}