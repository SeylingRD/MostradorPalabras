package com.example.mostradorpalabras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PalabraListAdapter  internal constructor(
    context: Context
) : RecyclerView.Adapter<PalabraListAdapter.palabrasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var palabras = emptyList<Palabra>() // Cached copy of words

    inner class palabrasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val palabraItemView: TextView = itemView.findViewById(R.id.textView2)
        val palabraItemView2: TextView = itemView.findViewById(R.id.textView3)
      //  val palabraItemView1: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): palabrasViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return palabrasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: palabrasViewHolder, position: Int) {
        val current = palabras[position]
        var tipopalabra = ""

        holder.palabraItemView.text = current.palabra.toString()
       holder.palabraItemView2.text= current.palabraMisk.toString()
        if (current.tipoPalabraId == 1) {
            tipopalabra = "Español"
        } else {
          tipopalabra = "Miskito"
        }
       // holder.palabraItemView1.text = tipopalabra


    }

    internal fun setPalabras(palabras: List<Palabra>) {
        this.palabras = palabras
        notifyDataSetChanged()
    }

    override fun getItemCount() = palabras.size
}

