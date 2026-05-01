package com.example.apppiece

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//A pesar de no tener dos actividades diferentes para la lista y para su "reproducción", como
//usamos un recyclerView, necesitamos un adaptador para relacionar esta lista con la actividad.
class OstAdapter(
    private val lista: List<Ost>,
    private val onClick: (Ost) -> Unit
) : RecyclerView.Adapter<OstAdapter.ViewHolder>(){

    //Al iniciar la vista nos aseguramos que no hay ninguna canción seleccionada.
    private var posicionSeleccionada =-1

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo: TextView = view.findViewById(R.id.txtTituloOst)
        val imagen: ImageView = view.findViewById(R.id.imgOst)
        //Creamos un valor contenedor para poder cambiar el fondo de la canción que se está
        //reproduciendo.
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

        //Aquí cambiamos el fondo de la canción que se está reproduciendo para distinguirla.
        if (position == posicionSeleccionada) {
            holder.contenedor.setBackgroundResource(R.drawable.fondo_ost_item_playing)
        } else {
            holder.contenedor.setBackgroundResource(R.drawable.fondo_ost_item)
        }

        //En este caso el holder, a parte de ver cual es la canción seleccionada, debe "apagar"
        //la canción que estaba seleccionada antes. Por eso guardamos ambas posiciones.
        holder.itemView.setOnClickListener {
            val posicionAnterior = posicionSeleccionada
            posicionSeleccionada = holder.bindingAdapterPosition
            //Notificamos del cambio y la  misma función se encarga de ver si debe tener un fondo
            //u otro.
            notifyItemChanged(posicionAnterior)
            notifyItemChanged(posicionSeleccionada)
            onClick(ost)
        }
    }

}