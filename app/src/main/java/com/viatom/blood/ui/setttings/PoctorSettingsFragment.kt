package com.viatom.blood.ui.setttings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.viatom.blood.databinding. PoctorFragmentSettingsBinding

class PoctorSettingsFragment : Fragment() {

    private var _binding:  PoctorFragmentSettingsBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val poctorSettingsViewModel =
            ViewModelProvider(this).get(PoctorSettingsViewModel::class.java)

        _binding =  PoctorFragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}