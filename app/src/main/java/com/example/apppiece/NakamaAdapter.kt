package com.example.apppiece

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Como los Nakama aparecen en un recyclerView, necesitamos un Adapter para relacionar las lista con
//el layout y con la NakamaActivity
class NakamaAdapter (
    private val lista: List<Nakama>,
    private val onClick: (Nakama) -> Unit
) : RecyclerView.Adapter<NakamaAdapter.ViewHolder>(){
    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val nombre : TextView = view.findViewById(R.id.txtNombreNakama)
        val imagen : ImageView = view.findViewById(R.id.imgNakama)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_nakama, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nakama = lista[position]

        holder.nombre.text = nakama.nombre
        holder.imagen.setImageResource(nakama.imagenNakama)

        holder.itemView.setOnClickListener {
            onClick(nakama)
        }
    }
}