package com.example.eva2copia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class crud : AppCompatActivity(), PulsacionesAdapter.OnItemClickListener {

    var dataSet: MutableList<Pulsaciones> = arrayListOf()
    lateinit var mAdapter: PulsacionesAdapter


    var isEditar = false
    var posicion = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crud)


        val rvList: RecyclerView = findViewById(R.id.rvList)
        val etPulsaciones: EditText = findViewById(R.id.etPulsaciones)
        val btnIngresar: Button = findViewById(R.id.btnIngresar)

        mAdapter = PulsacionesAdapter(this)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = mAdapter

        btnIngresar.setOnClickListener {
            val pulsacionesText = etPulsaciones.text.toString().trim()

            try {
                val pulsacionesValue = pulsacionesText.toInt()

                if (!isEditar) {
                    dataSet.add(Pulsaciones(pulsacionesValue))
                    mAdapter.submitList(dataSet)
                    mAdapter.notifyDataSetChanged()

                    etPulsaciones.setText("")
                } else {
                    dataSet[posicion].pulsaciones = pulsacionesValue
                    posicion = -1
                    isEditar = false
                    mAdapter.notifyDataSetChanged()

                    etPulsaciones.setText("")
                }
            } catch (e: NumberFormatException) {
                etPulsaciones.error = "Solo debe ingresar números"
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCambiarMenu: Button = findViewById(R.id.btnAtrasMenu)

        btnCambiarMenu.setOnClickListener{
            val intent = Intent(this, menu::class.java)
            startActivity(intent)

        }
        val btnVolverCRUD: Button = findViewById(R.id.btnVolverCRUD)

        btnVolverCRUD.setOnClickListener{
            val intent = Intent(this, menu::class.java)
            startActivity(intent)

        }

    }

    override fun onItemEditar(position: Int, item: Pulsaciones) {
        isEditar = true
        posicion = position
        findViewById<EditText>(R.id.etPulsaciones).setText(item.pulsaciones.toString())
    }

    override fun onItemBorrar(position: Int) {
        dataSet.removeAt(position)
        mAdapter.submitList(dataSet)
        mAdapter.notifyDataSetChanged()
    }
}

