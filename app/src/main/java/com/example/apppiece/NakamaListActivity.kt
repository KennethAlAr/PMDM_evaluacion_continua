package com.example.apppiece

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NakamaListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nakama_list)

        val recycler = findViewById<RecyclerView>(R.id.recyclerNakamas)
        recycler.layoutManager = GridLayoutManager(this, 3)

        val listaNakamas = listOf(
            Nakama("Luffy", "Monkey D. Luffy", "Mugiwara", getString(R.string.descripcion_luffy), R.drawable.nakama_luffy),
            Nakama("Zoro", "Roronoa Zoro", "Cazador de Piratas", getString(R.string.descripcion_zoro), R.drawable.nakama_zoro),
            Nakama("Nami", "Nami", "Gata Ladrona", getString(R.string.descripcion_nami), R.drawable.nakama_nami),
            Nakama("Usopp", "Usopp", "God Usopp", getString(R.string.descripcion_usopp), R.drawable.nakama_usopp),
            Nakama("Sanji", "Vinsmoke Sanji", "Kuro Ashi", getString(R.string.descripcion_sanji), R.drawable.nakama_sanji),
            Nakama("Chopper", "Tony Tony Chopper", "El Amante del Algodón de Azúcar", getString(R.string.descripcion_chopper), R.drawable.nakama_chopper),
            Nakama("Robin", "Nico Robin", "Akuma no Ko", getString(R.string.descripcion_robin), R.drawable.nakama_robin),
            Nakama("Franky", "Franky", "El Cyborg", getString(R.string.descripcion_franky), R.drawable.nakama_franky),
            Nakama("Brook", "Brook", "Soul King", getString(R.string.descripcion_brook), R.drawable.nakama_brook),
            Nakama("Jinbe", "Jinbe", "Caballero del Mar", getString(R.string.descripcion_jinbe), R.drawable.nakama_jinbe),
            Nakama("Going Merry", "Going Merry", "", getString(R.string.descripcion_merry), R.drawable.nakama_merry),
            Nakama("Thousand Sunny", "Thousand Sunny", "", getString(R.string.descripcion_sunny), R.drawable.nakama_sunny)
        )

        val adapter = NakamaAdapter(listaNakamas) { nakama ->
            val intent = Intent(this, NakamaActivity::class.java)
            intent.putExtra("imagen", nakama.imagenNakama)
            intent.putExtra("nombre", nakama.nombreCompleto)
            intent.putExtra("apodo", nakama.apodo)
            intent.putExtra("descripcion", nakama.descripcion)
            startActivity(intent)
        }

        recycler.adapter = adapter
    }


}