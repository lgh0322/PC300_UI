package com.vaca.pc300.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaca.pc300.MainApplication

@Database(entities = [PCdata::class], version = 1)
abstract class PcAppDatabase : RoomDatabase() {
    abstract fun pcDao(): PCDao
    companion object{
        val pc300db = Room.databaseBuilder(
            MainApplication.application,
            PcAppDatabase::class.java, "pc300-name"
        ).build()
    }
}