package com.viatom.blood.ui.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viatom.blood.PoctorGluDetailActivity
import com.viatom.blood.PoctorKetoneDetailActivity
import com.viatom.blood.PoctorUricDetailActivity
import com.viatom.blood.databinding.PoctorActivityGluDetailBinding
import com.viatom.blood.databinding. PoctorFragmentHistoryBinding
import com.viatom.blood.room.PoctorAppDatabase
import com.viatom.blood.ui.history.adapter.PoctorHistoryAdapter
import com.viatom.blood.ui.history.adapter.PoctorTopAdapter

class PoctorHistoryFragment : Fragment() {

    private var _binding:  PoctorFragmentHistoryBinding? = null

    private var currentType=PoctorAppDatabase.TYPE_POCTOR_GLU

    private val binding get() = _binding!!

    private lateinit var topAdapter: PoctorTopAdapter

    private lateinit var dataAdapter: PoctorHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val poctorHistoryViewModel =
            ViewModelProvider(this).get(PoctorHistoryViewModel::class.java)

        _binding =  PoctorFragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        topAdapter = PoctorTopAdapter(requireContext())
        dataAdapter = PoctorHistoryAdapter(requireContext())


        binding.topView.layoutManager =
            object : LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) {


                override fun canScrollHorizontally(): Boolean {
                    return false
                }
            }
        binding.topView.adapter = topAdapter

        binding.dataView.layoutManager =
            object : LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return true
                }

            }
        binding.dataView.adapter = dataAdapter


        dataAdapter.click=object :PoctorHistoryAdapter.Click{
            override fun clickItem(position: Int) {
                Log.e("gaga",position.toString())
                startActivity(Intent(requireActivity(), PoctorUricDetailActivity::class.java))
            }
        }




        topAdapter.click=object :PoctorTopAdapter.Click{
            override fun clickItem(position: Int) {
                Log.e("gaf",position.toString())
                currentType=position
            }
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}