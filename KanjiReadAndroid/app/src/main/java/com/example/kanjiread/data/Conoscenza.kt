package com.example.kanjiread.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "conoscenza_kanji")
class Conoscenza {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        private set
    @ColumnInfo(name = "percentage_number")
    var percentageNumb: Int = 0
}
