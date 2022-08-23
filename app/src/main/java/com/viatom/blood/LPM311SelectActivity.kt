package com.viatom.blood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.ActivityLpm311AboutBinding

class LPM311SelectActivity : AppCompatActivity() {

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