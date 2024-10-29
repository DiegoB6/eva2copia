package com.example.eva2copia

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSalirLogin: Button = findViewById(R.id.btnSalirLogin)

        btnSalirLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        val flechaLogin: Button = findViewById(R.id.flechaLogin)

        flechaLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val btnCambiarPantalla: Button = findViewById(R.id.btnIniciarSesion)

        btnCambiarPantalla.setOnClickListener {
            fnLogin()
    }
}

    private fun fnLogin() {
        //Cambiar pantalla
        try {
            val nombre: String = findViewById<EditText>(R.id.etNombre).text.toString()
            val contraseña: String = findViewById<EditText>(R.id.etContraseña).text.toString()

            if (nombre.isNotEmpty() && contraseña.isNotEmpty()) {
                val storedUsername = sharedPreferences.getString("Username", null)
                val storedPassword = sharedPreferences.getString("Password", null)

                if (storedUsername == null && storedPassword == null) {
                    //Guardar credenciales si es la primera vez que se ingresa
                    saveUserCredentials(nombre, contraseña)
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                    goToWelcomeScreen(nombre)
                } else if (nombre == storedUsername && contraseña == storedPassword) {
                    //verificar credenciales
                    goToWelcomeScreen(nombre)
                } else{
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Debe ingresar nombre y contraseña", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        }
    }
    private fun saveUserCredentials(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("Username", username)
        editor.putString("Password", password)
        editor.apply()
    }

    private fun goToWelcomeScreen(username: String) {
        val intent = Intent(this, menu::class.java)
        intent.putExtra("nombre", username)
        startActivity(intent)
    }
}