package com.example.apppiece

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OstActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null

    private lateinit var seekBarOst: SeekBar
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var btnPlayPause: ImageButton
    private var arrastrandoBarra = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ost)

        val recycler = findViewById<RecyclerView>(R.id.recyclerOst)
        val txtNowPlaying = findViewById<TextView>(R.id.txtNowPlaying)
        seekBarOst = findViewById(R.id.seekBarOst)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        val btnRewind = findViewById<ImageButton>(R.id.btnRewind)
        val btnForward = findViewById<ImageButton>(R.id.btnForward)

        player = ExoPlayer.Builder(this).build()

        recycler.layoutManager = LinearLayoutManager(this)

        val listaOst = listOf(
            Ost("Binkusu no Sake", R.drawable.thumbnail_binks_sake, R.raw.ost_binks_sake),
            Ost("Overtaken", R.drawable.thumbnail_overtaken, R.raw.ost_overtaken),
            Ost("We are! - Instrumental", R.drawable.thumbnail_we_are_ost, R.raw.ost_we_are_instrumental),
            Ost("Drums of Liberation", R.drawable.thumbnail_drums_liberation, R.raw.ost_drums_liberation)
        )

        val adapter = OstAdapter(listaOst) { ostClick ->
            txtNowPlaying.text = ostClick.tituloOst

            val uri = Uri.parse("android.resource://$packageName/${ostClick.audio}")
            val mediaItem = MediaItem.fromUri(uri)

            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.play()

            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)

            actualizarBarra()
        }

        recycler.adapter = adapter

        btnPlayPause.setOnClickListener {
            if (player?.isPlaying == true) {
                player?.pause()
                btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
            } else {
                player?.play()
                btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

        btnRewind.setOnClickListener {
            player?.let {
                val nuevaPosicion = it.currentPosition - 10000
                it.seekTo(if (nuevaPosicion < 0) 0 else nuevaPosicion)
            }
        }

        btnForward.setOnClickListener {
            player?.let {
                val nuevaPosicion = it.currentPosition + 10000
                it.seekTo(nuevaPosicion)
            }
        }

        seekBarOst.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                arrastrandoBarra = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let { progresoFinal ->
                    player?.seekTo(progresoFinal.toLong())
                }
                arrastrandoBarra = false
            }
        })

    }

    private fun actualizarBarra() {
        player?.let {
            if (it.isPlaying && !arrastrandoBarra) {
                if (it.duration > 0) {
                    seekBarOst.max = it.duration.toInt()
                }
                seekBarOst.progress = it.currentPosition.toInt()
            }
        }
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({ actualizarBarra() }, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}