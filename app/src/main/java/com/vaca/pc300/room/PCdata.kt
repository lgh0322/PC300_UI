package com.vaca.pc300.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PCdata(
    @PrimaryKey var date: Long=0L,
    @ColumnInfo(name = "dateString") var dateString: String="",
    @ColumnInfo(name = "id") var id: String="0",
    @ColumnInfo(name = "type") var type: Int=0,
    @ColumnInfo(name = "sys") var sys: Int=0,
    @ColumnInfo(name = "dia") var dia: Int=0,
    @ColumnInfo(name = "pr") var pr: Int=0,
    @ColumnInfo(name = "temp") var temp:Float=0f,
    @ColumnInfo(name = "note") var note: String="",

)