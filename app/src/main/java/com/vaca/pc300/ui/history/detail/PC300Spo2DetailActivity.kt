package com.vaca.pc300.ui.history.detail

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.databinding.ActivitySpo2DetailBinding
import com.vaca.pc300.ui.history.HistoryFragment

class PC300Spo2DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpo2DetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpo2DetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }


        HistoryFragment.currentSelect.observe(this){

        }

    }
}