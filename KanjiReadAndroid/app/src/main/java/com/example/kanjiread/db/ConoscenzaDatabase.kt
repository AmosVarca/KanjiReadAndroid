package com.example.kanjiread.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kanjiread.dao.ConoscenzaDao
import com.example.kanjiread.data.Conoscenza

@Database(entities = [Conoscenza::class], version = 1)
abstract class ConoscenzaDatabase : RoomDatabase() {

    abstract fun getConoscenzaDao(): ConoscenzaDao

    companion object {
        @Volatile
        private var database: ConoscenzaDatabase? = null

        fun getInstance(context: Context): ConoscenzaDatabase {
            return database ?: synchronized(this) {
                database ?: Room.databaseBuilder(
                    context.applicationContext,
                    ConoscenzaDatabase::class.java,
                    "database-conoscenza.db"
                ).build().also { database = it }
            }
        }
    }
}