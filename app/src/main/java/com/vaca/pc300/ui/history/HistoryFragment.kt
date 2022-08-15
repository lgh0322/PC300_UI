package com.vaca.pc300.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.R
import com.vaca.pc300.databinding.FragmentHistoryBinding
import com.vaca.pc300.ui.dashboard.adapter.PC300DataDetailAdapter
import com.vaca.pc300.ui.dashboard.adapter.SpaceItemDecoration3
import com.vaca.pc300.ui.history.adapter.LPM311HistoryAdapter
import com.vaca.pc300.ui.history.adapter.PC300HistoryLeftAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    companion object {
        val initJump = MutableLiveData<Int>()
    }

    private val binding get() = _binding!!
    var currentIndex = 0
    lateinit var navController: NavController
    private lateinit var leftAdapter: LPM311HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter=LPM311HistoryAdapter(requireContext())



        binding.leftView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.leftView.adapter =leftAdapter


        initJump.postValue(0)



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}