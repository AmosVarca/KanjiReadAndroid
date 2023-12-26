package com.example.prob

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object ListaConoscenzaUno {
    data class NumberItem(val id: Int, var value: Int)

    private const val FILE_NAME = "number_list.json"
    lateinit var applicationContext: Context
    private val gson = Gson()

    // Lista inizializzata da un file o creata se il file non esiste
    private val numberList: MutableList<NumberItem> by lazy {
        loadNumberListFromFile() ?: generateNumberList()
    }

    // Funzione per inizializzare la classe con il contesto
    fun initialize(context: Context) {
        applicationContext = context
    }

    // Getter per accedere alla lista in lettura
    val readOnlyNumberList: List<NumberItem>
        get() = numberList.toList()

    // Funzione per generare la lista di numeri con gli ID
    fun generateNumberList(): MutableList<NumberItem> {
        return (1..80).map { index ->
            NumberItem(index, (10 + index - 1))
        }.toMutableList()
    }

    // Funzione per creare il file e scrivere la lista nel file
    fun createAndWriteListToFile() {
        val file = File(applicationContext.filesDir, FILE_NAME)
        val jsonString = gson.toJson(numberList)
        file.writeText(jsonString)
    }

    // Funzione per caricare la lista da un file
    private fun loadNumberListFromFile(): MutableList<NumberItem>? {
        val file = File(applicationContext.filesDir, FILE_NAME)
        if (file.exists()) {
            val jsonString = file.readText()
            val typeToken = object : TypeToken<MutableList<NumberItem>>() {}.type
            return gson.fromJson(jsonString, typeToken)
        }
        return null
    }
}