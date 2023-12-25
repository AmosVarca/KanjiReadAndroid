package com.example.kanjiread.fragmentsProgress.placeholder.placeholder

import android.content.Context
import com.example.kanjiread.MainActivity
import com.example.kanjiread.R
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {




    private lateinit var appContext: Context
    fun initialize(context: Context) {
        appContext = context.applicationContext
    }
    //val resourceId = appContext.resources.getXml(R.xml.conoscenza_kanji)

















/**
* An array of sample (placeholder) items.
*/
val ITEMS: MutableList<PlaceholderItem> = ArrayList()

/**
* A map of sample (placeholder) items, by ID.
*/
val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

private var COUNT = 0

private var letters: Array<String> = emptyArray()

fun initializeStringArray(array: Array<String>) {
letters = array
COUNT = array.size

ITEMS.clear()
for (i in 1..COUNT) {
addItem(createPlaceholderItem(i))
}
}

fun getStringArray(): Array<String> {
return letters
}

fun addItem(item: PlaceholderItem) {
ITEMS.add(item)
ITEM_MAP.put(item.id, item)
}

private var conoscenza: Int = 0
private var updatable: Int = 0
private var percentage = 2
fun setConoscenza(letterId: String) {
updatable = 1
conoscenza ++
val item = ITEM_MAP["4"]
item?.percentage = conoscenza
}

fun createPlaceholderItem(position: Int): PlaceholderItem {
val content = if (position <= letters.size) letters[position - 1] else "Default"
val letterId = generateUniqueLetterId(position)
percentage = conoscenza // Imposta un valore predefinito per la percentuale
return PlaceholderItem(position.toString(), content, makeDetails(position), letterId, percentage)
}

private fun generateUniqueLetterId(position: Int): String {
// Modifica la logica per generare un identificatore univoco per la lettera in base alla posizione o ad altri criteri
return "$position"
}

private fun makeDetails(position: Int): String {
val builder = StringBuilder()
builder.append("Details about Item: ").append(position)
for (i in 0 until position) {
builder.append("\nMore details information here.")
}
return builder.toString()
}

/**
* A placeholder item representing a piece of content.
*/
data class PlaceholderItem(
val id: String,
val content: String,
val details: String,
val letterId: String,
var percentage: Int = 0
) {
override fun toString(): String = content
}
}
