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

        botonOpenings.setOnClickListener {
            val intent = Intent(this, OpeningListActivity::class.java)
            startActivity(intent)
        }

        botonCapitanes.setOnClickListener {
            val intent = Intent(this, CapitanesActivity::class.java)
            startActivity(intent)
        }
    }
}