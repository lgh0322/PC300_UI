package com.viatom.blood.ui.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.ActivityLpm311HistoryDetailBinding
import com.viatom.blood.room.LPM311AppDatabase
import com.viatom.blood.ui.history.LPM311HistoryFragment

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

    override fun onBackPressed() {
        val item=LPM311HistoryFragment.currentSelect.value
        if(item!=null){
            LPM311AppDatabase.updateNote(item.date,binding.note.text.toString())
        }
        super.onBackPressed()
    }
}