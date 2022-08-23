package com.viatom.blood

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.viatom.blood.databinding.PoctorActivityMainBinding
import com.viatom.blood.room.PoctorAppDatabase

class PoctorMainActivity : AppCompatActivity() {

    private lateinit var binding: PoctorActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PoctorActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList=null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

//        PoctorAppDatabase.savePoctorGlu(7.6f)
    }
}