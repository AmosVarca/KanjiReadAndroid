package com.example.kanjiread.fragmentsProgress.placeholder.placeholder

import android.content.Context
import android.util.Log
import com.example.kanjiread.MainActivity
import com.example.kanjiread.R
import java.util.ArrayList
import java.util.HashMap
import com.example.prob.ListaConoscenzaUno
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    private lateinit var applicationContext: Context
        fun initialize(context: Context) {
            applicationContext = context.applicationContext
    }
    fun fileDatabase(): Int {
        // Ottieni il riferimento al tuo file JSON
        var value: Int = 0
        val fileNamePlace = "number_list.json"
        val filePlace = File(ListaConoscenzaUno.applicationContext.filesDir, fileNamePlace)
        Log.d("ListaConoscenzaUnoPlace", "Il file esiste: ${filePlace.exists()}")
        val jsonString = filePlace.readText()

        // Deserializza la stringa JSON in una lista di oggetti
        val gson = Gson()
        val listType = object : TypeToken<List<ListaConoscenzaUno.NumberItem>>() {}.type
        val listaJson: List<ListaConoscenzaUno.NumberItem> = gson.fromJson(jsonString, listType)

        // Cerca l'oggetto con l'ID desiderato (nel tuo caso, ID 3)
        val desiredId = 3
        val itemWithDesiredId = listaJson.find { it.id == desiredId }

        // Se l'oggetto è stato trovato, stampa il valore associato all'ID
        if (itemWithDesiredId != null) {
            value = itemWithDesiredId.value
            Log.d("MainActivity", "Valore associato all'ID $desiredId: $value")
        }


        return value
    }

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
        percentage = fileDatabase() // Imposta un valore predefinito per la percentuale
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
