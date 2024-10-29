package com.example.eva2copia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPulso: Button = findViewById(R.id.btnPulsaciones)

        btnPulso.setOnClickListener{
            val intent = Intent(this, crud::class.java)
            startActivity(intent)

        }

        val flechaMenu: Button = findViewById(R.id.btnAtrasLogin)

        flechaMenu.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }

        val atrasMenu: Button = findViewById(R.id.btnCerrar)

        atrasMenu.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }

    }
}