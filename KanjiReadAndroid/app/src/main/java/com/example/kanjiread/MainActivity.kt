package com.example.kanjiread

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kanjiread.dao.ConoscenzaDao
import com.example.kanjiread.data.Conoscenza
import com.example.kanjiread.databinding.ActivityMainBinding
import com.example.kanjiread.db.ConoscenzaDatabase
import com.example.kanjiread.fragmentsProgress.placeholder.placeholder.PlaceholderContent
import com.example.prob.ListaConoscenzaUno
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val context: Context = this


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding



    /** Variabili per la generazione random dei Kanji */
    private val kanjiView: TextView by lazy {findViewById(R.id.kanjiView)}
    private val kanjiGenerator = KanjiGeneratorGradeOne()

    // Dichiarazione delle variabili come proprietà della classe
    private lateinit var kunReading: String
    private lateinit var onReading: String
    var conoscenza: Int = 0
    private var indiceKanji: Int = 0
    /**-------------------------------------------------*/

   /** Funzione di generazione */
   private fun generateRandomKanji() {
       // Chiamata al metodo per generare casualmente un kanji
       var randomKanji = kanjiGenerator.generateRandomKanji()
       // Utilizzo delle letture Kun e On
       kunReading = randomKanji.kunReading
       onReading = randomKanji.onReading
       conoscenza = randomKanji.conoscenza
       indiceKanji = KanjiGeneratorGradeOne().ottieniRandomIndex()

       // Mostra la lettera generata nel TextView
       kanjiView.text = randomKanji.character.toString()
   }
    /**-------------------------------------------------*/

    /** Aggiornamento della percentuale -----------------*/
    private fun updatePercentageForCurrentKanji(): Int {
        // Trova l'elemento nella lista con l'ID corrispondente
        //PlaceholderContent.setConoscenza("4")
        // Aggiorna la percentuale per l'elemento corrente
        return 1
    }
    /**-----------------------------------------------------*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** ---- USO FILE JSON COME DATABASE --------*/
        ListaConoscenzaUno.initialize(applicationContext)
        // Genera la lista con gli ID
        //ListaConoscenzaUno.generateNumberList()
        ListaConoscenzaUno.createAndWriteListToFile()
        // Verifica l'esistenza del file dopo l'inizializzazione
        val file = File(applicationContext.filesDir, "number_list.json")
        Log.d("ListaConoscenzaUno", "Il file esiste: ${file.exists()}")
        val jsonString = file.readText()

        // Deserializza la stringa JSON in una lista di oggetti
        val gson = Gson()
        val listType = object : TypeToken<List<ListaConoscenzaUno.NumberItem>>() {}.type
        val listaJson: List<ListaConoscenzaUno.NumberItem> = gson.fromJson(jsonString, listType)

        // Cerca l'oggetto con l'ID desiderato (nel tuo caso, ID 3)
        val desiredId = 3
        val itemWithDesiredId = listaJson.find { it.id == desiredId }

        // Se l'oggetto è stato trovato, stampa il valore associato all'ID
        if (itemWithDesiredId != null) {
            var value = itemWithDesiredId.value
            Log.d("MainActivity", "Valore associato all'ID $desiredId: $value")

            itemWithDesiredId.value = 300 // Sostituisci 'nuovoValore' con il valore desiderato
            Log.d("MainActivity", "Valore aggiornato per l'ID $desiredId: ${itemWithDesiredId.value}")

            // Serializza la lista aggiornata in una stringa JSON
            val updatedJsonString = gson.toJson(listaJson)

            // Scrivi la stringa JSON nel file
            file.writeText(updatedJsonString)
            value = itemWithDesiredId.value
            Log.d("MainActivity", "Valore associato all'ID $desiredId: $value")
            Log.d("MainActivity", "File JSON aggiornato con successo.")

        } else {
            Log.d("MainActivity", "Nessun oggetto trovato con l'ID $desiredId")
        }
        // Stampa la stringa JSON nel log
        Log.d("ListaConoscenzaUno", "Contenuto del file JSON: $jsonString")
        /**-----------------------------------------------------------*/



        // Imposta il layout dell'activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val buttonControlla  = findViewById<Button>(R.id.controlla)
        val buttonNonRicordo   = findViewById<Button>(R.id.nonRicordo)
        val editTextKun      = findViewById<EditText>(R.id.editTextKunReading)
        val editTextOn       = findViewById<EditText>(R.id.editTextOnReading)


        val builderOk    = AlertDialog.Builder(this)
        val builderNotOk = AlertDialog.Builder(this)
        val builderEmpty = AlertDialog.Builder(this)

        builderNotOk.setPositiveButton("Sbagliato,riprova") { dialog, _ ->
            editTextKun.setText("")
            editTextOn.setText("")
            dialog.dismiss()}

        generateRandomKanji()

        // Aggiungi un listener per gestire il clic sul pulsante
        buttonControlla.setOnClickListener {
            // Ottieni il testo inserito dall'utente nell'EditText
            val kunInserito = editTextKun.text.toString()
            val onInserito = editTextOn.text.toString()

            // Esegui azioni basate sul testo inserito
            if (kunInserito.isNotBlank() || onInserito.isNotBlank()) {
                // Se il testo non è vuoto, mostra un Toast con il testo inserito
                //if (kunInserito == kunReading && onInserito == onReading) {
                if (kunInserito == "A" && onInserito == "B") {
                    builderOk.setMessage("Testo inserito: $kunInserito e $onInserito sono corretti")
                    builderOk.setPositiveButton("OK,prossimo Kanji") { dialog, _ ->
                        generateRandomKanji()
                        updatePercentageForCurrentKanji()
                        editTextKun.setText("")
                        editTextOn.setText("")
                        dialog.dismiss()}
                    val dialog = builderOk.create()
                    dialog.show()
                } else {
                    if (kunInserito != kunReading && onInserito != onReading) {

                        builderNotOk.setMessage(
                            "Testo inserito: $kunInserito e $onInserito sono errati"
                        ).create().show()
                    } else if (kunInserito != kunReading){
                        builderNotOk.setMessage("Il KUN inserito $kunInserito è errato").create().show()
                    } else {
                        builderNotOk.setMessage("Il ON inserito $onInserito è errato").create().show()
                    }
                }
            } else {
                // Se il testo è vuoto, mostra un Toast con un messaggio informativo
                builderEmpty.setMessage("Compila entrambe le opzioni prima di premere il pulsante").create().show()
            }
        }
        buttonNonRicordo.setOnClickListener{
            generateRandomKanji()
            editTextKun.setText("")
            editTextOn.setText("")
        }
    }
}
