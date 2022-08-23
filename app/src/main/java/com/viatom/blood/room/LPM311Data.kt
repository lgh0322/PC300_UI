package com.viatom.blood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LPM311Data(
    @PrimaryKey var date: Long = 0L,
    @ColumnInfo(name = "dateString") var dateString: String = "",
    @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo(name = "chol") var chol: Double = 0.0,
    @ColumnInfo(name = "trig") var trig: Double = 0.0,
    @ColumnInfo(name = "hdl") var hdl: Double = 0.0,
    @ColumnInfo(name = "ldl") var ldl: Double = 0.0,
    @ColumnInfo(name = "cholhdl") var cholhdl: Double = 0.0,
    @ColumnInfo(name = "note") var note: String = "",
    )