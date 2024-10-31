package com.example.cuestionario_remastered

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var data = intent.extras
        var userId = data?.getString("ID");
        var puntuacion = data?.getDouble("Puntuacion");

        var btnSiguiente = findViewById<Button>(R.id.btnSiguiente3);
        var radioGroupPregunta = findViewById<RadioGroup>(R.id.radioGroupPregunta3)

        fun correccion(respuesta : String) : Boolean {
            var estaBien = false;
            if (respuesta.equals("78")) {
                estaBien = true;
            }
            return estaBien;
        }

        btnSiguiente.setOnClickListener{
            var radioPreguntaId = radioGroupPregunta.checkedRadioButtonId;
            if (radioPreguntaId != -1) {
                var selectedAnswer = findViewById<RadioButton>(radioPreguntaId);
                var selectedText = selectedAnswer.text.toString();
                if (correccion(selectedText)) {
                    puntuacion = puntuacion?.plus(1.0);
                }
            }
            var intent = Intent(this, Pregunta4::class.java);
            var bundle = Bundle();
            bundle.putString("ID", userId);
            bundle.putDouble("Puntuacion", puntuacion!!);
            intent.putExtras(bundle);
            startActivity(intent)
        }

    }
}