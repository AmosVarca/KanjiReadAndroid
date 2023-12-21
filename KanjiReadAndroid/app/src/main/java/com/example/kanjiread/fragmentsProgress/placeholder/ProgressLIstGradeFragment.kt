package com.example.kanjiread.fragmentsProgress.placeholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kanjiread.R

/**
 * A fragment representing a list of Items.
 */
class ProgressLIstGradeFragment : Fragment() {

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
         if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                // Ottieni l'array di stringhe da resources
                val stringArray = resources.getStringArray(R.array.array_grado_2)

                // Ottieni il riferimento al RecyclerView dal layout
                //val recyclerView: RecyclerView = view.findViewById(R.id.listRecyclerViewOne)

                // Crea un adapter personalizzato e imposta l'array di stringhe come dati
                val adapter = MyProgressLIstGradeRecyclerViewAdapter(stringArray.toMutableList())

                // Imposta il LinearLayoutManager per il RecyclerView
                //recyclerView.layoutManager = LinearLayoutManager(context)

                // Imposta l'adapter per il RecyclerView
                //recyclerView.adapter = adapter

                //adapter = MyProgressLIstGradeRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }


        // Ottieni l'array di stringhe da resources
        /*val stringArray = resources.getStringArray(R.array.array_grado_1)

        // Ottieni il riferimento al RecyclerView dal layout
        val recyclerView: RecyclerView = view.findViewById(R.id.listRecyclerViewOne)

        // Crea un adapter personalizzato e imposta l'array di stringhe come dati
        val adapter = MyProgressLIstGradeRecyclerViewAdapter(stringArray.toList())

        // Imposta il LinearLayoutManager per il RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Imposta l'adapter per il RecyclerView
        recyclerView.adapter = adapter*/

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProgressLIstGradeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}