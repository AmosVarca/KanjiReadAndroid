package com.example.kanjiread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class MainActivityProgress : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = "Progressi"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Abilita il tasto di ritorno nella Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Visualizzazione degli elementi
        setContentView(R.layout.activity_main_progress)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Gestisci il clic sul tasto di ritorno
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}