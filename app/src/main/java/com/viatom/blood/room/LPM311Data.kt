package com.viatom.blood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LPM311Data(
    @PrimaryKey var date: Long = 0L,
    @ColumnInfo(name = "dateString") var dateString: String = "",
    @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo(name = "chol") var chol: Float = 0f,
    @ColumnInfo(name = "trig") var trig: Float = 0f,
    @ColumnInfo(name = "hdl") var hdl: Float = 0f,
    @ColumnInfo(name = "ldl") var ldl: Float = 0f,
    @ColumnInfo(name = "cholhdl") var cholhdl: Float = 0f,
    @ColumnInfo(name = "note") var note: String = "",
    )