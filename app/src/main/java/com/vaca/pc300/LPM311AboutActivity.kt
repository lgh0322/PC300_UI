package com.vaca.pc300

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vaca.pc300.databinding.ActivityLpm311AboutBinding
import com.vaca.pc300.databinding.ActivityLpm311HistoryDetailBinding
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.databinding.ActivityTempDetailBinding

class LPM311AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLpm311AboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityLpm311AboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}