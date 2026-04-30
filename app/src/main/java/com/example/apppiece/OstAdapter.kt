package com.example.apppiece

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OstAdapter(
    private val lista: List<Ost>,
    private val onClick: (Ost) -> Unit
) : RecyclerView.Adapter<OstAdapter.ViewHolder>(){

    private var posicionSeleccionada =-1

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo: TextView = view.findViewById(R.id.txtTituloOst)
        val imagen: ImageView = view.findViewById(R.id.imgOst)
        val contenedor: View = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_ost, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ost = lista[position]

        holder.titulo.text = ost.tituloOst
        holder.imagen.setImageResource(ost.imagenOst)

        if (position == posicionSeleccionada) {
            holder.contenedor.setBackgroundResource(R.drawable.fondo_ost_item_playing)
        } else {
            holder.contenedor.setBackgroundResource(R.drawable.fondo_ost_item)
        }

        holder.itemView.setOnClickListener {
            val posicionAnterior = posicionSeleccionada
            posicionSeleccionada = holder.bindingAdapterPosition
            notifyItemChanged(posicionAnterior)
            notifyItemChanged(posicionSeleccionada)
            onClick(ost)
        }
    }

}