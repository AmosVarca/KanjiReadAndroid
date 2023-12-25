package com.example.kanjiread.dao

import androidx.room.*
import com.example.kanjiread.data.Conoscenza

@Dao
interface ConoscenzaDao {
    @Insert
    fun creaConoscenza(conoscenza: Conoscenza): Long
    @Update
    fun updateConoscenza(conoscenza: Conoscenza): Void
    @Delete
    fun eliminaConoscenza(conoscenza: Conoscenza): Void
    @Query("SELECT * FROM conoscenza_kanji WHERE id = :idConoscenza")
    fun findConoscenzaById(idConoscenza: Long): Conoscenza?
}
