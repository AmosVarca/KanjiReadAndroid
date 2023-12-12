package com.example.kanjiread
import kotlin.random.Random

class kanjiGeneratorGradeOne{
    data class Kanji(val character: String, val kunReading: String, val onReading: String)

    // Array di oggetti Kanji rappresentanti i primi 10 kanji di Grado 1
    private val kanjiList = arrayOf(
    Kanji("一", "ひと-つ", "イチ"),
    Kanji("二", "ふた-つ", "ニ"),
    Kanji("三", "みっ-つ", "サン"),
    Kanji("四", "よん", "シ"),
    Kanji("五", "いつ-つ", "ゴ"),
    Kanji("六", "むっ-つ", "ロク"),
    Kanji("七", "なな", "シチ"),
    Kanji("八", "やっ-つ", "ハチ"),
    Kanji("九", "ここの-つ", "キュウ"),
    Kanji("十", "とお", "ジュウ")
)

    // Metodo per generare casualmente un kanji e ottenere le sue letture Kun e On
    fun generateRandomKanji(): Kanji {
        val randomIndex = Random.nextInt(kanjiList.size)
        return kanjiList[randomIndex]
    }
}