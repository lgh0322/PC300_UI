package com.viatom.blood.ui.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.ActivityLpm311HistoryDetailBinding

class LPM311HistoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLpm311HistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityLpm311HistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }
}