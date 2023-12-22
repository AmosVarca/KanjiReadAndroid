package com.example.kanjiread
import kotlin.random.Random

class KanjiGeneratorGradeOne{
    data class Kanji(val character: String, val kunReading: String, val onReading: String, var conoscenza: Int)
    var randomIndex: Int = 0

    fun ottieniRandomIndex(): Int {
        return randomIndex
    }

        // Array di oggetti Kanji rappresentanti i primi 10 kanji di Grado 1
    private val kanjiList = arrayOf(
        Kanji("一", "ひとつ", "イチ", 0),
        Kanji("右", "みぎ", "ウ",0),
        Kanji("雨", "あめ", "ウ",0),
        Kanji("円", "えん", "エン",0),
        Kanji("王", "おう", "オウ",0),
        Kanji("音", "おと", "オン",0),
        Kanji("下", "した", "カ",0),
        Kanji("火", "ひ", "カ",0),
        Kanji("花", "はな", "カ",0),
        Kanji("貝", "かい", "カイ",0),
        Kanji("学", "がく", "ガク",0),
        Kanji("気", "き", "キ",0),
        Kanji("九", "きゅう", "キュウ",0),
        Kanji("休", "やすむ", "キュウ",0),
        Kanji("玉", "たま", "ギョク",0),
        Kanji("金", "かね", "キン",0),
        Kanji("空", "そら", "クウ",0),
        Kanji("月", "つき", "ゲツ",0),
        Kanji("犬", "いぬ", "ケン",0),
        Kanji("見", "みる", "ケン",0),
        Kanji("五", "ご", "ゴ",0),
        Kanji("口", "くち", "コウ",0),
        Kanji("校", "こう", "コウ",0),
        Kanji("左", "ひだり", "サ",0),
        Kanji("三", "さん", "サン",0),
        Kanji("山", "やま", "サン",0),
        Kanji("子", "こ", "シ",0),
        Kanji("四", "し", "シ",0),
        Kanji("糸", "いと", "シ",0),
        Kanji("字", "じ", "ジ",0),
        Kanji("耳", "みみ", "ジ",0),
        Kanji("七", "なな", "シチ",0),
        Kanji("車", "くるま", "シャ",0),
        Kanji("手", "て", "シュ",0),
        Kanji("十", "とお", "ジュウ",0),
        Kanji("出", "でる", "シュツ",0),
        Kanji("女", "おんな", "ジョ",0),
        Kanji("小", "ちいさい", "ショウ",0),
        Kanji("上", "うえ", "ジョウ",0),
        Kanji("森", "もり", "シン",0),
        Kanji("人", "ひと", "ジン",0),
        Kanji("水", "みず", "スイ",0),
        Kanji("正", "ただしい", "セイ",0),
        Kanji("生", "いきる", "セイ",0),
        Kanji("青", "あお", "セイ",0),
        Kanji("夕", "ゆう", "セキ",0),
        Kanji("石", "いし", "セキ",0),
        Kanji("赤", "あか", "セキ",0),
        Kanji("千", "せん", "セン",0),
        Kanji("川", "かわ", "セン",0),
        Kanji("先", "さき", "セン",0),
        Kanji("早", "はやい", "ソウ",0),
        Kanji("草", "くさ", "ソウ",0),
        Kanji("足", "あし", "ソク",0),
        Kanji("村", "むら", "ソン",0),
        Kanji("大", "おおきい", "ダイ",0),
        Kanji("男", "おとこ", "ダン",0),
        Kanji("竹", "たけ", "チク",0),
        Kanji("中", "なか", "チュウ",0),
        Kanji("虫", "むし", "チュウ",0),
        Kanji("町", "まち", "チョウ",0),
        Kanji("天", "てん", "テン",0),
        Kanji("田", "た", "デン",0),
        Kanji("土", "つち", "ド",0),
        Kanji("二", "ふた", "ニ",0),
        Kanji("日", "ひ", "ニチ",0),
        Kanji("入", "はいる", "ニュウ",0),
        Kanji("年", "ねん", "ネン",0),
        Kanji("白", "しろ", "ハク",0),
        Kanji("八", "やっつ", "ハチ",0),
        Kanji("百", "ひゃく", "ヒャク",0),
        Kanji("文", "ふみ", "ブン",0),
        Kanji("木", "き", "モク",0),
        Kanji("本", "ほん", "ホン",0),
        Kanji("名", "な", "メイメイ",0),
        Kanji("目", "め", "モク",0),
        Kanji("立", "たつ", "リツ",0),
        Kanji("力", "ちから", "リョク",0),
        Kanji("林", "はやし", "リン",0),
        Kanji("六", "むっつ", "ロク",0),
)

    // Metodo per generare casualmente un kanji e ottenere le sue letture Kun e On 0
    fun generateRandomKanji(): Kanji {
        randomIndex = Random.nextInt(kanjiList.size)
        return kanjiList[randomIndex]
    }
}