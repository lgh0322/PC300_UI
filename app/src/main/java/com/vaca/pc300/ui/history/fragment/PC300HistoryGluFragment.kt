package com.vaca.pc300.ui.history.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.ui.history.detail.PC300GluDetailActivity
import com.vaca.pc300.databinding.FragmentHistoryGluBinding
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.ui.history.HistoryFragment
import com.vaca.pc300.ui.history.adapter.PC300HistoryGluAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                HistoryFragment.currentSelect.postValue(leftAdapter.mData.get(position))
                startActivity(Intent(requireActivity(), PC300GluDetailActivity::class.java))
            }
        }

        PcAppDatabase.dataScope.launch {
            val a= PcAppDatabase.pc300db.pcDao().getAllR(PcAppDatabase.TYPE_GLU)
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