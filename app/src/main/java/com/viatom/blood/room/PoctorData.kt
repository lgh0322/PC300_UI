package com.viatom.blood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PoctorData(
    @PrimaryKey var date: Long = 0L,
    @ColumnInfo(name = "dateString") var dateString: String = "",
    @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo(name = "type") var type: Int = 0,
    @ColumnInfo(name = "glu") var glu: Float = 0f,
    @ColumnInfo(name = "ketone") var ketone: Float = 0f,
    @ColumnInfo(name = "uric") var uric: Float = 0f,
    @ColumnInfo(name = "note") var note: String = "",

    )