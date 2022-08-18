package com.vaca.pc300.room

import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONException

class ConverterDoubleToString {
    @TypeConverter
    fun JSONArrayfromDoubleArray(values: DoubleArray?): String {
        if(values==null){
            return ""
        }
        val jsonArray = JSONArray()
        for (value in values) {
            try {
                jsonArray.put(value)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun JSONArrayToDoubleArray(values: String?): DoubleArray? {
        try {
            val jsonArray = JSONArray(values)
            val doubleArray = DoubleArray(jsonArray.length())
            for (i in 0 until jsonArray.length()) {
                doubleArray[i] = jsonArray.getString(i).toDouble()
            }
            return doubleArray
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }
}