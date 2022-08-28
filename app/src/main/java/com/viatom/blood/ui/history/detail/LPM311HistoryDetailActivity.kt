package com.viatom.blood.ui.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.viatom.blood.databinding.ActivityLpm311HistoryDetailBinding
import com.viatom.blood.room.LPM311AppDatabase
import com.viatom.blood.ui.history.LPM311HistoryFragment
import com.viatom.blood.utils.PathUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class LPM311HistoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLpm311HistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityLpm311HistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }

        LPM311HistoryFragment.currentSelect.observe(this){
            val file= File(PathUtil.getPathX(it.name))
            Glide.with(this)
                .load(file)
                .into(binding.img)
        }
    }

    override fun onBackPressed() {

        super.onBackPressed()
    }
}