package com.vaca.pc300.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.R
import com.vaca.pc300.databinding.FragmentHistoryBinding
import com.vaca.pc300.room.PCdata
import com.vaca.pc300.ui.history.adapter.PC300HistoryLeftAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    companion object {
        val initJump = MutableLiveData<Int>()
        val currentSelect=MutableLiveData<PCdata>()
    }

    private val binding get() = _binding!!
    var currentIndex = 0
    lateinit var navController: NavController
    private lateinit var leftAdapter: PC300HistoryLeftAdapter
    val topId = arrayOf(
        R.id.PC300HistoryBPFragment,
        R.id.PC300HistorySPO2Fragment,
        R.id.PC300HistoryTempFragment,
        R.id.PC300HistoryGluFragment,
        R.id.PC300HistoryECGFragment,
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter= PC300HistoryLeftAdapter(requireContext())

        leftAdapter.click=object :PC300HistoryLeftAdapter.Click{
            override fun clickItem(position: Int) {
                if (!navController.popBackStack(topId[position], false)) {
                    try {
                        navController.navigate(topId[position])
                    } catch (e: java.lang.Exception) {

                    }
                }
            }
        }

        binding.leftView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.leftView.adapter =leftAdapter


        initJump.postValue(0)


        val fm = childFragmentManager.findFragmentById(R.id.bx) as NavHostFragment
        navController = fm.navController
        val graph = navController.navInflater.inflate(R.navigation.pc300_history_navigation)
        initJump.observe(viewLifecycleOwner) {
            currentIndex = it
            graph.setStartDestination(topId[it])
            navController.graph = graph
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}