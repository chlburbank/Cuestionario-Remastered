package com.example.cuestionario_remastered

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var data = intent.extras;
        var userId = data?.getString("ID");
        var puntuacion = data?.getDouble("Puntuacion");

        var listaPreguntas = findViewById<ListView>(R.id.listaPreguntas);

        var items = arrayOf("Punto Nemo", "Mariannas Trench", "Montaña Everest", "Amazonas", "Groenlandia")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listaPreguntas.adapter = adapter

        listaPreguntas.setOnItemClickListener{parent, view, position, id ->
            var respuestaSeleccionada = items[position];
            if (correccion(respuestaSeleccionada)) {
                puntuacion = puntuacion!!.plus(1.0);
            }
            var intent = Intent(this, Pregunta5::class.java);
            var bundle = Bundle();
            bundle.putString("ID", userId);
            bundle.putDouble("Puntuacion", puntuacion!!);
            intent.putExtras(bundle);
            startActivity(intent)
        }
    }

    fun correccion(respuesta : String) : Boolean {
        var estaBien = false;
        if (respuesta.equals("Punto Nemo")) {
            estaBien = true;
        }
        return estaBien;
    }

}