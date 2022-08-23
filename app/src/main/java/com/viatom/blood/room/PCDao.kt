package com.viatom.blood.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PCDao {
    @Query("SELECT * FROM PCdata")
    fun getAll(): List<PCdata>

    @Query("SELECT * FROM PCdata  ORDER BY date DESC")
    fun getAllR(): List<PCdata>


    @Query("SELECT * FROM PCdata WHERE type=:type ORDER BY date DESC")
    fun getAllR(type:Int): List<PCdata>


    @Query("SELECT * FROM PCdata WHERE date IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<PCdata>

    @Insert
    fun insert(vararg item: PCdata)


    @Delete
    fun delete(PCdata: PCdata)


    @Query("DELETE FROM PCdata WHERE date = :myDate")
    fun deleteByDate(myDate: Long)




    @Query("UPDATE PCdata SET note=:myNote WHERE date = :date")
    fun updateNote(myNote: String, date: Long)

    @Query("SELECT note FROM PCdata WHERE date = :date")
    fun getNote(date: Long):String



    @Query("SELECT * FROM PCdata WHERE date = :date")
    fun loadByDate(date: Long): PCdata


    @Query("UPDATE PCdata SET id=:id WHERE dateString = :dateString")
    fun updateId(id: String, dateString: String)





    @Query("SELECT * FROM PCdata WHERE dateString =:dateString")
    fun getPCdataByDateString(dateString: String): List<PCdata>


    @Query("SELECT * FROM PCdata WHERE dateString =:dateString LIMIT 1")
    fun getPCdataByDateString2(dateString: String): List<PCdata>

    @Query("SELECT * FROM PCdata WHERE date = :myDate")
    fun loadByIds(myDate: Long): PCdata

    @Query("DELETE FROM PCdata")
    fun delete()

    @Query("DELETE FROM PCdata where date NOT IN (SELECT date from PCdata ORDER BY date DESC LIMIT 100)")
    fun deleteOld()
}