package com.viatom.blood.ui.setttings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.viatom.blood.databinding.Lpm311FragmentSettingsBinding

class LPM311SettingsFragment : Fragment() {

    private var _binding: Lpm311FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val LPM311SettingsViewModel =
            ViewModelProvider(this).get(LPM311SettingsViewModel::class.java)

        _binding = Lpm311FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}