package com.viatom.blood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LPM311Data(
    @PrimaryKey  var name: String = "",

    @ColumnInfo(name = "dateString") var dateString: String = "",
    @ColumnInfo(name = "date") var date: Long = 0L,
    @ColumnInfo(name = "size") var size:Int=1,
    @ColumnInfo(name = "note") var note: String = "",
    )