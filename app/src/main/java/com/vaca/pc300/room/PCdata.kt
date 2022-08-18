package com.vaca.pc300.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PCdata(
    @PrimaryKey var date: Long=0L,
    @ColumnInfo(name = "dateString") var dateString: String="",
    @ColumnInfo(name = "id") var id: String="",
    @ColumnInfo(name = "type") var type: Int=0,
    @ColumnInfo(name = "sys") var sys: Int=0,
    @ColumnInfo(name = "dia") var dia: Int=0,
    @ColumnInfo(name = "pr") var pr: Int=0,
    @ColumnInfo(name = "o2") var o2: Int=0,
    @ColumnInfo(name = "temp") var temp:Float=0f,
    @ColumnInfo(name = "glu") var glu:Float=0f,
    @ColumnInfo(name = "hr") var hr: Int=0,
    @ColumnInfo(name = "ecg_data") var ecg_data: DoubleArray?=null,
    @ColumnInfo(name = "ecg_result") var ecg_result: String="",
    @ColumnInfo(name = "note") var note: String="",

    )