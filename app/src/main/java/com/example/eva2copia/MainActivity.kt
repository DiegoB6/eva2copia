package com.example.eva2copia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPantallaCrud: Button = findViewById(R.id.btnCrud)

        btnPantallaCrud.setOnClickListener{
            val intent = Intent(this, crud::class.java)
            startActivity(intent)

        }


        val btnPantallaMenu: Button = findViewById(R.id.btnMenu)

        btnPantallaMenu.setOnClickListener{
            val intent = Intent(this, menu::class.java)
            startActivity(intent)

        }


    }
}