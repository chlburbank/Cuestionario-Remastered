package com.example.cuestionario_remastered

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pregunta5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pregunta5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var data = intent.extras;
        var userId = data?.getString("ID");
        var puntuacion = data?.getDouble("Puntuacion");

        var btnFinalizar = findViewById<Button>(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener{
            var intent = Intent(this, ResultadosActivity::class.java);
            var bundle = Bundle();
            bundle.putString("ID", userId);
            bundle.putDouble("Puntuacion", puntuacion!!);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}