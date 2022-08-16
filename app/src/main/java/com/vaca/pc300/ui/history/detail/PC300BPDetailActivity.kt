package com.vaca.pc300.ui.history.detail

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vaca.pc300.databinding.ActivityBpDetailBinding
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.ui.history.HistoryFragment

class PC300BPDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBpDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBpDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }


        HistoryFragment.currentSelect.observe(this){

            binding.sys.text=it.sys.toString()
            binding.dia.text=it.dia.toString()
            binding.pr.text=it.pr.toString()

            binding.bpView.setBPValue(180,40)
        }
    }
}