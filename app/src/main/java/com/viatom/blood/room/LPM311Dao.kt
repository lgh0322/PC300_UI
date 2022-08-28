package com.viatom.blood.room

import androidx.room.*

@Dao
interface LPM311Dao {
    @Query("SELECT * FROM LPM311Data")
    fun getAll(): List<LPM311Data>

    @Query("SELECT * FROM LPM311Data  ORDER BY date DESC")
    fun getAllR(): List<LPM311Data>





    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg item: LPM311Data)


    @Delete
    fun delete(LPM311Data: LPM311Data)


    @Query("DELETE FROM LPM311Data WHERE date = :myDate")
    fun deleteByDate(myDate: Long)


    @Query("UPDATE LPM311Data SET note=:myNote WHERE date = :date")
    fun updateNote(myNote: String, date: Long)

    @Query("SELECT note FROM LPM311Data WHERE date = :date")
    fun getNote(date: Long):String



    @Query("SELECT * FROM LPM311Data WHERE date = :date")
    fun loadByDate(date: Long): LPM311Data





    @Query("SELECT * FROM LPM311Data WHERE dateString =:dateString")
    fun getPCdataByDateString(dateString: String): List<LPM311Data>


    @Query("SELECT * FROM LPM311Data WHERE dateString =:dateString LIMIT 1")
    fun getPCdataByDateString2(dateString: String): List<LPM311Data>

    @Query("SELECT * FROM LPM311Data WHERE date = :myDate")
    fun loadByIds(myDate: Long): LPM311Data

    @Query("DELETE FROM LPM311Data")
    fun delete()

    @Query("DELETE FROM LPM311Data where date NOT IN (SELECT date from LPM311Data ORDER BY date DESC LIMIT 100)")
    fun deleteOld()
}