package com.viatom.blood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LPM311Data(
    @PrimaryKey var date: Long = 0L,
    @ColumnInfo(name = "dateString") var dateString: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "note") var note: String = "",
    )