package com.example.kanjiread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.example.kanjiread.fragmentsProgress.placeholder.ProgressLIstGradeFragment
import com.google.android.material.tabs.TabLayout

class MainActivityProgress : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = "Progressi"
    }

    override fun onResume() {
        super.onResume()


        /** Inizializziamo il fragment -------------------------------------*/
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val progressLIstGradeFragment = ProgressLIstGradeFragment()

        // Sostituisci R.id.fragment_container con l'ID del tuo contenitore nel layout dell'activity
        transaction.replace(R.id.listaProgressiKanjiGrado1, progressLIstGradeFragment)
        // Se decommento questa  riga non ho il ritorno immediato all'activity precedente
        // transaction.addToBackStack(null) // Aggiungi alla pila per la navigazione all'indietro
        transaction.commit()
        /** -------------------------------------------------------------- */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Abilita il tasto di ritorno nella Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Visualizzazione degli elementi
        setContentView(R.layout.activity_main_progress)

        /** il FrameLayout è inizialmente nascosto (con android:visibility="gone" nel layout XML).
         * Quando l'utente seleziona il primo tab, il FrameLayout diventa visibile, mentre per gli altri tab viene nascosto */
        val tabLayout: TabLayout = findViewById(R.id.tabLayout123)
        val frameLayout: FrameLayout = findViewById(R.id.listaProgressiKanjiGrado1)

        // Imposta il listener per il TabLayout
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Controlla quale tab è stato selezionato
                when (tab.position) {
                    0 -> frameLayout.visibility = View.VISIBLE // Mostra il FrameLayout per la prima opzione
                    else -> frameLayout.visibility = View.GONE // Nascondi il FrameLayout per le altre opzioni
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Non è necessario implementare nulla qui
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Non è necessario implementare nulla qui
            }
        })
        /** ---------------------------------------------------------------------------------------------
         * ----------------------------------------------------------------------------------------------
         * ----------------------------------------------------------------------------------------------*/

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