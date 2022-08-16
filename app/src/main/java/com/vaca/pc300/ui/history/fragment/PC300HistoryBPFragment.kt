package com.vaca.pc300.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.MainApplication
import com.vaca.pc300.ui.history.detail.PC300BPDetailActivity
import com.vaca.pc300.databinding.FragmentHistoryBpBinding
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.ui.history.HistoryFragment
import com.vaca.pc300.ui.history.adapter.PC300HistoryBpAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                HistoryFragment.currentSelect.postValue(leftAdapter.mData.get(position))
                startActivity(Intent(requireActivity(), PC300BPDetailActivity::class.java))
            }
        }

        MainApplication.dataScope.launch {
            val a=PcAppDatabase.pc300db.pcDao().getAllR()
            Log.e("faa",a.size.toString())
            withContext(Dispatchers.Main){
                leftAdapter.addAll(a)
            }

        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}