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
import com.example.kanjiread.fragmentsProgress.placeholder.placeholder.PlaceholderContent

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

                // Ottieni il riferimento al RecyclerView dal layout
                val recyclerView: RecyclerView = view.findViewById(R.id.listRecyclerViewOne)
                val letterList = createLetterList()

                // Imposta il LinearLayoutManager per il RecyclerView
                //recyclerView.layoutManager = LinearLayoutManager(context)

                // Imposta l'adapter per il RecyclerView
                recyclerView.adapter = adapter

                PlaceholderContent.initializeStringArray(letterList)

                // Ora puoi accedere a PlaceholderContent.STRING_ARRAY dall'interno di AltraClasse
                adapter = MyProgressLIstGradeRecyclerViewAdapter(PlaceholderContent.ITEMS, PlaceholderContent.getStringArray())
            }
        }
        return view
    }

    private fun createLetterList(): Array<String> {
        return resources.getStringArray(R.array.array_grado_1)
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