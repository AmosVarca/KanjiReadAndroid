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
import com.example.kanjiread.kanjiGeneratorGradeOne

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Imposta il layout dell'activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val button      = findViewById<Button>(R.id.controlla)
        val editTextKun = findViewById<EditText>(R.id.editTextKunReading)
        val editTextOn  = findViewById<EditText>(R.id.editTextOnReading)


        val builder = AlertDialog.Builder(this)

        // Ottieni il riferimento al TextView nel layout utilizzando l'ID
        val kanjiView: TextView = findViewById(R.id.kanjiView)
        // Creazione di un'istanza della classe kanjiGeneratorGradeOne
        val kanjiGenerator = kanjiGeneratorGradeOne()
        // Chiamata al metodo per generare casualmente un kanji
        val randomKanji = kanjiGenerator.generateRandomKanji()
        // Esempio di utilizzo delle letture Kun e On
        val kunReading = randomKanji.kunReading
        val onReading = randomKanji.onReading

        println("Kanji: ${randomKanji.character}")
        println("Lettura Kun: ${randomKanji.kunReading}")
        println("Lettura On: ${randomKanji.onReading}")

        // Mostra la lettera generata nel TextView
        kanjiView.text = randomKanji.character.toString()

        // Aggiungi un listener per gestire il clic sul pulsante
        button.setOnClickListener {
            // Ottieni il testo inserito dall'utente nell'EditText
            val kunInserito = editTextKun.text.toString()
            val onInserito = editTextOn.text.toString()

            // Esegui azioni basate sul testo inserito
            if (kunInserito.isNotBlank() || onInserito.isNotBlank()) {
                // Se il testo non è vuoto, mostra un Toast con il testo inserito
                if (kunInserito == randomKanji.kunReading && onInserito == randomKanji.onReading) {
                    builder.setMessage("Testo inserito: $kunInserito e $onInserito sono corretti")
                    builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}
                    val dialog = builder.create()
                    dialog.show()
                } else {
                    if (kunInserito != randomKanji.kunReading && onInserito != randomKanji.onReading) {
                        builder.setMessage(
                            "Testo inserito: $kunInserito e $onInserito sono errati"
                        )
                        val dialog1 = builder.create()
                        dialog1.show()
                    } else if (kunInserito != randomKanji.kunReading){
                        Toast.makeText(this, "Il KUN inserito $kunInserito è errato", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Il ON inserito $onInserito è errato", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                // Se il testo è vuoto, mostra un Toast con un messaggio informativo
                Toast.makeText(this, "Inserisci del testo prima di premere il pulsante", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
