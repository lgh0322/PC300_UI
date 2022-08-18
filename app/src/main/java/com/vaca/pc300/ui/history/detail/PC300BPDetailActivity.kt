package com.vaca.pc300.ui.history.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vaca.pc300.databinding.ActivityBpDetailBinding
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.ui.history.HistoryFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PC300BPDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBpDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBpDetailBinding.inflate(layoutInflater)
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

            binding.sys.text=it.sys.toString()
            binding.dia.text=it.dia.toString()
            binding.pr.text=it.pr.toString()

            PcAppDatabase.dataScope.launch {
                val note= PcAppDatabase.pc300db.pcDao().getNote(it.date)
                withContext(Dispatchers.Main){
                    binding.note.setText(note)
                }
            }


            binding.bpView.setBPValue(it.sys,it.dia)
        }
    }

    override fun onBackPressed() {
        val item=HistoryFragment.currentSelect.value
        if(item!=null){
            PcAppDatabase.updateNote(item.date,binding.note.text.toString())
        }
        super.onBackPressed()
    }
}