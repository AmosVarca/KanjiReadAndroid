package com.example.kanjiread

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kanjiread.databinding.ActivityMainBinding
import com.example.kanjiread.KanjiGeneratorGradeOne

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    /** Variabili per la generazione random dei Kanji */
    private val kanjiView: TextView by lazy {findViewById(R.id.kanjiView)}
    private val kanjiGenerator = KanjiGeneratorGradeOne()

    // Dichiarazione delle variabili come proprietà della classe
    private lateinit var kunReading: String
    private lateinit var onReading: String
   /**-------------------------------------------------*/

   /** Funzione di generazione */
   private fun generateRandomKanji() {
       // Chiamata al metodo per generare casualmente un kanji
       var randomKanji = kanjiGenerator.generateRandomKanji()
       // Utilizzo delle letture Kun e On
       kunReading = randomKanji.kunReading
       onReading = randomKanji.onReading

       //println("Kanji: ${randomKanji.character}")
       //println("Lettura Kun: ${randomKanji.kunReading}")
       //println("Lettura On: ${randomKanji.onReading}")

       // Mostra la lettera generata nel TextView
       kanjiView.text = randomKanji.character.toString()
   }
    /**-------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                if (kunInserito == kunReading && onInserito == onReading) {
                    builderOk.setMessage("Testo inserito: $kunInserito e $onInserito sono corretti")
                    builderOk.setPositiveButton("OK,prossimo Kanji") { dialog, _ ->
                        generateRandomKanji()
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
