package com.example.apppiece

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VideoActivity : AppCompatActivity() {

    //He cambiado el VideoView por la biblioteca media3. De esta manera podemos utilizar su controlador
    //multimedia que se puede modificar y usar mejor que el media player que viene por defecto en
    //Android Studio
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val playerView = findViewById<PlayerView>(R.id.playerView)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtAutor = findViewById<TextView>(R.id.txtAutor)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val videoId = intent.getIntExtra("video", 0)
        val titulo = intent.getStringExtra("titulo")
        val autor = intent.getStringExtra("autor")
        val descripcion = intent.getStringExtra("descripcion")

        txtTitulo.text = titulo
        txtAutor.text = "Por: $autor"
        txtDescripcion.text = descripcion

        //Cargamos Exoplayer para usar el controllador de media de media3
        player = ExoPlayer.Builder(this).build()
        playerView.player = player


        val uri = Uri.parse("android.resource://$packageName/$videoId")
        //En vez de utilizar el videoView, guardamos la uri en un MediaItem
        val mediaItem = MediaItem.fromUri(uri)

        //Usamos el mediaItem para cargar el video y reproducirlo con el Exoplayer
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()
    }

    //La función onDestroy() sirve para cerrar el video al abandonar la actividad.
    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }

}