package com.viatom.littlePu.pc100.pc100room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PCdata(
    @PrimaryKey val date: Long,
    @ColumnInfo(name = "dateString") val dateString: String,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "o2") val o2: Int,
    @ColumnInfo(name = "o2pr") val o2pr: Int,
    @ColumnInfo(name = "sys") val sys: Int,
    @ColumnInfo(name = "dia") val dia: Int,
    @ColumnInfo(name = "pr") val pr: Int,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "member") val member: String = "",
)