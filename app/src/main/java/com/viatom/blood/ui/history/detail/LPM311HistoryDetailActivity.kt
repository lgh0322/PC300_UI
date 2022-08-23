package com.viatom.blood.ui.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.ActivityLpm311HistoryDetailBinding
import com.viatom.blood.room.LPM311AppDatabase
import com.viatom.blood.ui.history.LPM311HistoryFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            binding.time.text=it.dateString
            binding.chol.text=it.chol.toString()
            binding.hdl.text=it.hdl.toString()
            binding.ldl.text=it.ldl.toString()
            binding.cholHdl.text=it.cholhdl.toString()
            binding.trig.text=it.trig.toString()

            LPM311AppDatabase.dataScope.launch {
                val note= LPM311AppDatabase.lpmDb.lpmDao().getNote(it.date)
                withContext(Dispatchers.Main){
                    binding.note.setText(note)
                }
            }
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