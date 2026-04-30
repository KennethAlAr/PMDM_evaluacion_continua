package com.example.apppiece

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OpeningListActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_list)

        val recycler = findViewById<RecyclerView>(R.id.recyclerOpenings)
        recycler.layoutManager = LinearLayoutManager(this)

        val listaOpenings = listOf(
            Video("We are! 1000th Anniversary Edition", "Hiroshi Kitadani", getString(R.string.descripcion_we_are), R.drawable.thumbnail_we_are, R.raw.video_we_are),
            Video("Brand New World", "D-51", getString(R.string.descripcion_brand_new_world), R.drawable.thumbnail_brand_new_world, R.raw.video_brand_new_world),
            Video("Kokoro no Chizu", "BOYSTYLE", getString(R.string.descripcion_kokoro_no_chizu), R.drawable.thumbnail_kokoro_no_chizu, R.raw.video_kokoro_no_chizu)
        )

        val adapter = VideoAdapter(listaOpenings) { video ->
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("video", video.video)
            intent.putExtra("titulo", video.titulo)
            intent.putExtra("autor", video.autor)
            intent.putExtra("descripcion", video.descripcion)
            startActivity(intent)
        }

        recycler.adapter = adapter

    }
}