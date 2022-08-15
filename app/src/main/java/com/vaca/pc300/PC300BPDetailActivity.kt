package com.vaca.pc300

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vaca.pc300.databinding.ActivityBpDetailBinding
import com.vaca.pc300.databinding.ActivityMainBinding

class PC300BPDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBpDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBpDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}