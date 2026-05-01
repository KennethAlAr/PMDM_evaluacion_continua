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

    //Para la reproducción y control del audio utilizaremos la biblioteca media3.
    //Así podemos usar el controlador Exoplayer, que nos da mas libertad para modificar el media
    //controller que el media player de Android Studio
    //Creamos seekBarOst y btnPlayPause con lateinit para poder utilizarlas en actualizarBarra()
    //y para que el btnPlayPause quede esperando a ver si se inicia una canción.
    private var player: ExoPlayer? = null

    //Para la barra de progreso utilizamos una seekBar con un Handler
    private lateinit var seekBarOst: SeekBar
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var btnPlayPause: ImageButton

    //Creamos una variable arrastrandoBarra pra ver si el usuario está tocando la barra de progreso.
    //Si no la está arrastrando actualizarBarra() hace su trabajo normalmente. Si el usuario está
    //arrastrando la barra, actualizarBarra() se detiene, así evitamos que cada 500 milisegundos
    //el thumb vuelva a la posición que dbeería estar, saliendo del arrastre del usuario.
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

        //Cargamos el controlador Exoplayer de media3.
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
            //Igual que con VideoActivity, en vez de utilizar el videoView, guardamos la uri en un MediaItem
            val mediaItem = MediaItem.fromUri(uri)

            //Usamos el mediaItem para cargar el video y reproducirlo con el Exoplayer
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.play()

            //Si se inicia una canción el botón de Play cambia su imagen a botón de Pausa
            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)

            //Iniciamos la función actualizarBarra() para que el thumb se mueva a medida que
            //avanza la canción.
            actualizarBarra()
        }

        recycler.adapter = adapter

        //El botón play/pause cambia su imagen segun suena o no la canción y tambien la inicia o
        //la pausa
        btnPlayPause.setOnClickListener {
            if (player?.isPlaying == true) {
                player?.pause()
                btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
            } else {
                player?.play()
                btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

        //El botón rewind mira en que posición se encuentra la canción y le resta 10 segundos.
        //Si la posición final es menor a 0, deja la posición en 0.
        btnRewind.setOnClickListener {
            player?.let {
                val nuevaPosicion = it.currentPosition - 10000
                it.seekTo(if (nuevaPosicion < 0) 0 else nuevaPosicion)
            }
        }

        //El botón fast forward mira en que posición se encuentra la canción y le suma 10 segundos.
        btnForward.setOnClickListener {
            player?.let {
                val nuevaPosicion = it.currentPosition + 10000
                it.seekTo(nuevaPosicion)
            }
        }

        //Aquí controlamos si el usuario mueve la barra de progreso.
        seekBarOst.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //Usamos el onProgressChanged para saber que el usuario está tocando la barra,
            //pero no hacemos nada para que la canción se siga reproduciendo normalmente.
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            //Si el usuario empieza a mover la barra, pasamos la variable arrastrandoBarra a true
            //para que actualizarBarra() deje de controlarla y pase a control del usuario.
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                arrastrandoBarra = true
            }

            //Cuando el usuario suelta el thumb, miramos en que posicion está y le decimos al player
            //que reproduzca desde ahí.
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let { progresoFinal ->
                    player?.seekTo(progresoFinal.toLong())
                }
                //Después devolvemos el control de la barra a actualizarBarra()
                arrastrandoBarra = false
            }
        })

    }

    //actualizarBarra() controla en que posición se encuentra la música y actualiza sin para la
    //posición del thumb
    private fun actualizarBarra() {
        player?.let {
            //La barra solo actualiza si la música está sonando y si el usuario no está arrastrando
            //el thumb
            if (it.isPlaying && !arrastrandoBarra) {
                if (it.duration > 0) {
                    seekBarOst.max = it.duration.toInt()
                }
                seekBarOst.progress = it.currentPosition.toInt()
            }
        }
        //Esta parte es para que no amontonen actualizaciones de la barra, si hay alguna que
        //no haya podido actualizar, se borra cuando aparece una nueva.
        handler.removeCallbacksAndMessages(null)
        //La barra comprueba su posición cada 500milisegundos.
        handler.postDelayed({ actualizarBarra() }, 500)
    }

    //La función onDestroy() sirve para cerrar el video al abandonar la actividad.
    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}