package com.viatom.blood.ui.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viatom.blood.databinding.PoctorActivityGluDetailBinding
import com.viatom.blood.room.PoctorAppDatabase
import com.viatom.blood.ui.history.PoctorHistoryFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PoctorGluDetailActivity : AppCompatActivity() {

    private lateinit var binding: PoctorActivityGluDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PoctorActivityGluDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }


        PoctorHistoryFragment.currentSelect.observe(this){
            binding.glu.text=it.value.toString()
            PoctorAppDatabase.dataScope.launch {
                val note= PoctorAppDatabase.poctorDb.pcDao().getNote(it.date)
                withContext(Dispatchers.Main){
                    binding.note.setText(note)
                }
            }
        }
    }

    override fun onBackPressed() {
        val item=PoctorHistoryFragment.currentSelect.value
        if(item!=null){
            PoctorAppDatabase.updateNote(item.date,binding.note.text.toString())
        }
        super.onBackPressed()
    }
}