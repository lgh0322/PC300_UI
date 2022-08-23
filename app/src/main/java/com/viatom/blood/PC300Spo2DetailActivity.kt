package com.viatom.blood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.ActivitySpo2DetailBinding

class PC300Spo2DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpo2DetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpo2DetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }

    }
}