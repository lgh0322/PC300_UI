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
import com.vaca.pc300.databinding.ActivityEcgDetailBinding
import com.vaca.pc300.databinding.ActivityMainBinding
import com.vaca.pc300.room.PcAppDatabase
import com.vaca.pc300.ui.history.HistoryFragment

class PC300EcgDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEcgDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityEcgDetailBinding.inflate(layoutInflater)
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