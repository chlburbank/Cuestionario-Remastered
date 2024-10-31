package com.example.cuestionario_remastered

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var puntuacion = 0.0;

        var data = intent.extras;
        var userId = data?.getString("ID");
        var spinner = findViewById<Spinner>(R.id.spinnerPregunta);
        var btnSiguiente = findViewById<Button>(R.id.btnSiguiente);

        var items = listOf("India", "Filipinas", "America");
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.adapter = adapter;

        fun correccion(respuesta:String) : Boolean {
            var estaBien = false;
            if (respuesta.equals("America")) {
                estaBien = true
            }
            return estaBien;
        }

        btnSiguiente.setOnClickListener{
            var respuesta = spinner.selectedItem.toString();
            if (correccion(respuesta)) {
                puntuacion = puntuacion + 1.0;
            }
            var intent = Intent(this, Pregunta2::class.java);
            var bundle = Bundle();
            bundle.putString("ID", userId);
            bundle.putDouble("Puntuacion", puntuacion);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}