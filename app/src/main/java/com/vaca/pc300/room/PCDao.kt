package com.viatom.littlePu.pc100.pc100room

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

    @Query("SELECT * FROM PCdata WHERE member=:member  ORDER BY date DESC")
    fun getAllR(member: String): List<PCdata>

    @Query("SELECT * FROM PCdata WHERE member=:member  ORDER BY date DESC LIMIT :num")
    fun getAllRx1(member: String, num: Int): List<PCdata>

    @Query("SELECT * FROM PCdata WHERE member=:member  ORDER BY date DESC LIMIT 2")
    fun getAllR1(member: String): List<PCdata>

    @Query("SELECT * FROM PCdata WHERE member=:member AND sys<>0 AND dia<>0 AND pr<>0 ORDER BY date DESC")
    fun getAllR2(member: String): List<PCdata>

    @Query("SELECT * FROM PCdata WHERE date IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<PCdata>

    @Insert
    fun insert(vararg item: PCdata)


    @Delete
    fun delete(PCdata: PCdata)


    @Query("DELETE FROM PCdata WHERE date = :myDate")
    fun deleteByDate(myDate: Long)


    @Query("DELETE FROM PCdata WHERE member = :member")
    fun deleteByMember(member: String)

    @Query("UPDATE PCdata SET note=:myNote WHERE date = :date")
    fun updateNote(myNote: String, date: Long)


    @Query("UPDATE PCdata SET member=:newMember WHERE member = :old")
    fun updateMember(old: String, newMember: String)

    @Query("SELECT * FROM PCdata WHERE date = :date")
    fun loadByDate(date: Long): PCdata


    @Query("UPDATE PCdata SET id=:id WHERE dateString = :dateString")
    fun updateId(id: String, dateString: String)


    @Query("UPDATE PCdata SET id=:id,member=:member WHERE dateString = :dateString")
    fun updateId2(id: String, dateString: String, member: String)


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