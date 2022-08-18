package com.vaca.pc300.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PCdata(
    @PrimaryKey val date: Long,
    @ColumnInfo(name = "dateString") val dateString: String,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "sys") val sys: Int,
    @ColumnInfo(name = "dia") val dia: Int,
    @ColumnInfo(name = "pr") val pr: Int,
    @ColumnInfo(name = "temp") val temp:Float,
    @ColumnInfo(name = "note") val note: String,

)