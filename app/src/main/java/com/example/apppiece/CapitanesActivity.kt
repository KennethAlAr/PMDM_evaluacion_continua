package com.example.apppiece

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class CapitanesActivity : AppCompatActivity() {

    private val listaCapitanes = mutableListOf<Capitan>()

    private lateinit var imgCapitan: ImageView
    private lateinit var txtNombre: TextView
    private lateinit var txtTripulacion: TextView
    private lateinit var btnCapitan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capitanes)

        imgCapitan = findViewById(R.id.imgCapitan)
        txtNombre = findViewById(R.id.txtNombre)
        txtTripulacion = findViewById(R.id.txtTripulacion)
        btnCapitan = findViewById(R.id.btnCapitan)

        cargarPersonajes()

        btnCapitan.setOnClickListener {
            if(listaCapitanes.isNotEmpty()){
                val capitanRandom = listaCapitanes.random()
                mostrarGanador(capitanRandom)
            }
        }
    }

    private fun cargarPersonajes() {
        try{
            val jsonString = assets.open("capitanes.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val capitan = jsonArray.getJSONObject(i)
                val nuevoCapitan = Capitan(
                    nombre = capitan.getString("nombre"),
                    tripulacion = capitan.getString("tripulacion"),
                    imagen = capitan.getString("imagen")
                )
                listaCapitanes.add(nuevoCapitan)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun mostrarGanador(capitan : Capitan) {
        txtNombre.text = capitan.nombre
        txtTripulacion.text = capitan.tripulacion

        val idImagen = resources.getIdentifier(capitan.imagen, "drawable", packageName)

        if (idImagen != 0) {
            imgCapitan.setImageResource(idImagen)
        } else {
            imgCapitan.setImageResource(R.drawable.pj_unknown)
        }
    }
}