package com.viatom.blood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.viatom.blood.databinding.PoctorActivityKetoneDetailBinding

class PoctorKetoneDetailActivity : AppCompatActivity() {

    private lateinit var binding: PoctorActivityKetoneDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PoctorActivityKetoneDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}