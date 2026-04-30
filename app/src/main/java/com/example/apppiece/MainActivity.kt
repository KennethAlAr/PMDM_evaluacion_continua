package com.example.apppiece

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonOpenings = findViewById<Button>(R.id.btnOpenings)
        val botonCapitanes = findViewById<Button>(R.id.btnCapitanes)
        val botonNakamas = findViewById<Button>(R.id.btnNakamas)
        val botonOst = findViewById<Button>(R.id.btnOST)

        botonOpenings.setOnClickListener {
            val intent = Intent(this, OpeningListActivity::class.java)
            startActivity(intent)
        }

        botonCapitanes.setOnClickListener {
            val intent = Intent(this, CapitanesActivity::class.java)
            startActivity(intent)
        }

        botonNakamas.setOnClickListener {
            val intent = Intent(this, NakamaListActivity::class.java)
            startActivity(intent)
        }

        botonOst.setOnClickListener {
            val intent = Intent(this, OstActivity::class.java)
            startActivity(intent)
        }
    }
}