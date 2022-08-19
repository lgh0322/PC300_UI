package com.vaca.pc300.ui.history.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.databinding.ActivitySpo2DetailBinding
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.ui.history.HistoryFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PC300Spo2DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpo2DetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpo2DetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.note.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.noteNumber.text = "${binding.note.text.toString().length}/400"
            }

        })

        HistoryFragment.currentSelect.observe(this){
            binding.o2.text=it.o2.toString()
            binding.pr.text=it.pr.toString()
            binding.time.text=it.dateString
            PcAppDatabase.dataScope.launch {
                val note= PcAppDatabase.pc300db.pcDao().getNote(it.date)
                withContext(Dispatchers.Main){
                    binding.note.setText(note)
                }
            }

        }

    }


    override fun onBackPressed() {
        val item= HistoryFragment.currentSelect.value
        if(item!=null){
            PcAppDatabase.updateNote(item.date,binding.note.text.toString())
        }
        super.onBackPressed()
    }
}