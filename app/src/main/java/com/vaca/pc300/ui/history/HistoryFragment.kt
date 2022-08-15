package com.vaca.pc300.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaca.pc300.databinding.FragmentHistoryBinding
import com.vaca.pc300.ui.history.adapter.LPM311HistoryAdapter
import com.vaca.pc300.ui.history.adapter.PoctorTopAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null


    private val binding get() = _binding!!
    var currentIndex = 0
    lateinit var navController: NavController
    private lateinit var topAdapter: PoctorTopAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        topAdapter= PoctorTopAdapter(requireContext())



        binding.topView.layoutManager = object :   LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) {


            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        binding.topView.adapter =topAdapter







        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}