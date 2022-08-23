package com.viatom.blood.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PoctorDao {
    @Query("SELECT * FROM PoctorData")
    fun getAll(): List<PoctorData>

    @Query("SELECT * FROM PoctorData  ORDER BY date DESC")
    fun getAllR(): List<PoctorData>


    @Query("SELECT * FROM PoctorData WHERE type=:type ORDER BY date DESC")
    fun getAllR(type:Int): List<PoctorData>


    @Query("SELECT * FROM PoctorData WHERE date IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<PoctorData>

    @Insert
    fun insert(vararg item: PoctorData)


    @Delete
    fun delete(PoctorData: PoctorData)


    @Query("DELETE FROM PoctorData WHERE date = :myDate")
    fun deleteByDate(myDate: Long)




    @Query("UPDATE PoctorData SET note=:myNote WHERE date = :date")
    fun updateNote(myNote: String, date: Long)

    @Query("SELECT note FROM PoctorData WHERE date = :date")
    fun getNote(date: Long):String



    @Query("SELECT * FROM PoctorData WHERE date = :date")
    fun loadByDate(date: Long): PoctorData


    @Query("UPDATE PoctorData SET id=:id WHERE dateString = :dateString")
    fun updateId(id: String, dateString: String)





    @Query("SELECT * FROM PoctorData WHERE dateString =:dateString")
    fun getPCdataByDateString(dateString: String): List<PoctorData>


    @Query("SELECT * FROM PoctorData WHERE dateString =:dateString LIMIT 1")
    fun getPCdataByDateString2(dateString: String): List<PoctorData>

    @Query("SELECT * FROM PoctorData WHERE date = :myDate")
    fun loadByIds(myDate: Long): PoctorData

    @Query("DELETE FROM PoctorData")
    fun delete()

    @Query("DELETE FROM PoctorData where date NOT IN (SELECT date from PoctorData ORDER BY date DESC LIMIT 100)")
    fun deleteOld()
}