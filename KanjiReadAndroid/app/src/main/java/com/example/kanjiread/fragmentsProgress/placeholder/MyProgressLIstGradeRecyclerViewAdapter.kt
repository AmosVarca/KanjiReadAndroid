package com.example.kanjiread.fragmentsProgress.placeholder

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.kanjiread.databinding.FragmentItemBinding

import com.example.kanjiread.fragmentsProgress.placeholder.placeholder.PlaceholderContent.PlaceholderItem

private val String.content: CharSequence?
    get() {
        TODO("Not yet implemented")
    }
private val String.id: CharSequence?
    get() {
        TODO("Not yet implemented")
    }

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyProgressLIstGradeRecyclerViewAdapter(
    private val values: MutableList<PlaceholderItem>,
    private val stringArray: Array<String>
) : RecyclerView.Adapter<MyProgressLIstGradeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        //holder.contentView.text = item.content
        holder.letterView.text = item.content

        // Aggiorna il campo percentageView con la percentuale di PlaceholderItem
        holder.percentageView.text = "${item.percentage}%"

        // Assicurati che la posizione sia valida nell'array di stringhe
        if (position < stringArray.size) {
            // Usa l'array di stringhe nella tua vista (o fai qualsiasi altra cosa necessaria)
            val letter = stringArray[position]
            // Aggiungi la lettera alla tua vista (assicurati di avere la vista adeguata)
            holder.letterView.text = letter
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        //val contentView: TextView = binding.content
        val letterView: TextView = binding.content  // Aggiunto campo per la lettera
        val percentageView: TextView = binding.percentageView  // Aggiunto campo per la percentuale

        override fun toString(): String {
            return super.toString() + " '" + letterView.text + "'"
        }
    }

}