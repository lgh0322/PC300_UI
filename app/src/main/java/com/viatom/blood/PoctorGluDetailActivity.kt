package com.viatom.blood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.PoctorActivityGluDetailBinding

class PoctorGluDetailActivity : AppCompatActivity() {

    private lateinit var binding: PoctorActivityGluDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PoctorActivityGluDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}