package com.example.cuestionario_remastered

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var data = intent.extras
        var userId = data?.getString("ID");
        var puntuacion = data?.getDouble("Puntuacion")


        var spinnerPregunta = findViewById<Spinner>(R.id.spinnerPregunta2);
        var items = listOf("2018", "2022", "2024");
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPregunta.adapter = adapter;

        var btnSiquiente = findViewById<Button>(R.id.btnSiguiente2);

        fun correccion(respuesta:String) : Boolean {
            var estaBien = false;
            if (respuesta.equals("2022")) {
                estaBien = true
            }
            return estaBien;
        }

        btnSiquiente.setOnClickListener{
            var respuesta = spinnerPregunta.selectedItem.toString();
            if (correccion(respuesta)) {
                puntuacion = puntuacion?.plus(1.0)
            }
            var intent = Intent(this, Pregunta3::class.java);
            var bundle = Bundle();
            bundle.putString("ID", userId);
            bundle.putDouble("Puntuacion", puntuacion!!);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}