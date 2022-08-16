package com.vaca.pc300.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PCdata::class], version = 3)
abstract class PcAppDatabase : RoomDatabase() {
    abstract fun pcDao(): PCDao
}