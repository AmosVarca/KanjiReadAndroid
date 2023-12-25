package com.example.kanjiread.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kanjiread.dao.ConoscenzaDao
import com.example.kanjiread.data.Conoscenza

@Database(entities = [Conoscenza::class], version = 1)
abstract class ConoscenzaDatabase : RoomDatabase() {
    private lateinit var database: ConoscenzaDatabase

    abstract fun conoscenzaDao(): ConoscenzaDao

    fun getInstance(context: Context): ConoscenzaDatabase {
        if (database == null){
            database = Room.databaseBuilder(
                context,
                ConoscenzaDatabase::class.java,
                "database-conoscenza.db"
            ).build()

        }
        return database
    }
}
