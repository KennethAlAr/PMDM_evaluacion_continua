package com.example.apppiece

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


//Esta Activity es una actividad normal que muestra imagenes y texto
class NakamaActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nakama)

        val imgImagen = findViewById<ImageView>(R.id.imgDetailNakama)
        val txtNombre = findViewById<TextView>(R.id.txtDetailNombre)
        val txtApodo = findViewById<TextView>(R.id.txtDetailApodo)
        val txtDescripcion = findViewById<TextView>(R.id.txtDetailDescripcion)
        val imagenId = intent.getIntExtra("imagen", 0)
        val nombre = intent.getStringExtra("nombre")
        val apodo = intent.getStringExtra("apodo")
        val descripcion = intent.getStringExtra("descripcion")

        txtNombre.text = nombre
        txtApodo.text = apodo
        txtDescripcion.text = descripcion

        if (imagenId != 0) {
            imgImagen.setImageResource(imagenId)
        }

    }
}