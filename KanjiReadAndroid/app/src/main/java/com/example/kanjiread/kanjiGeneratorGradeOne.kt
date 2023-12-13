package com.example.kanjiread
import kotlin.random.Random

class KanjiGeneratorGradeOne{
    data class Kanji(val character: String, val kunReading: String, val onReading: String)

    // Array di oggetti Kanji rappresentanti i primi 10 kanji di Grado 1
    private val kanjiList = arrayOf(
    Kanji("一", "ひとつ", "イチ"),
    Kanji("二", "ふたつ", "ニ"),
    Kanji("三", "みっつ", "サン"),
    Kanji("四", "よん", "シ"),
    Kanji("五", "いつつ", "ゴ"),
    Kanji("六", "むっつ", "ロク"),
    Kanji("七", "なな", "シチ"),
    Kanji("八", "やっつ", "ハチ"),
    Kanji("九", "ここのつ", "キュウ"),
    Kanji("十", "とお", "ジュウ")
)

    // Metodo per generare casualmente un kanji e ottenere le sue letture Kun e On
    fun generateRandomKanji(): Kanji {
        val randomIndex = Random.nextInt(kanjiList.size)
        return kanjiList[randomIndex]
    }
}