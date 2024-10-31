package com.example.cuestionario_remastered

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var labelUser = findViewById<TextView>(R.id.labelUser);
        var labelPuntuacion = findViewById<TextView>(R.id.labelPuntuacion);
        var btnVolver = findViewById<Button>(R.id.btnVolver);

        var data = intent.extras;
        var userId = data!!.getString("ID");
        var puntuacion = data.getDouble("Puntuacion")

        setResults(userId.toString(), puntuacion, labelUser, labelPuntuacion);

        btnVolver.setOnClickListener{
            guardarYVolver(puntuacion, userId.toString());
            var intentBackToMenu = Intent(this,  MainActivity::class.java);
            intentBackToMenu.putExtra("ID", userId);
            startActivity(intentBackToMenu);
        }

    }

    fun setResults(userId: String, puntuacion: Double, labelUser: TextView, labelPuntuacion: TextView) {
        var admin = AdminSQLiteOpenHelper(this, "users", null, 1);
        var db = admin.readableDatabase;
        var query = db.rawQuery("SELECT username FROM users WHERE id = ?" , arrayOf(userId))
        if (query.moveToFirst()) {
            labelUser.setText(labelUser.text.toString() + " " + query.getString(0));
            labelPuntuacion.setText(labelPuntuacion.text.toString() + " " + puntuacion);
        }
    }

    fun guardarYVolver(puntuacion : Double, userId: String) {
        var admin = AdminSQLiteOpenHelper(this, "users", null, 1)
        var db = admin.writableDatabase;
        var registry = ContentValues();
        registry.put("score", puntuacion);
        var update = db.update("users", registry, "id = ?", arrayOf(userId))
        db.close()
        if (update == 1) {
            Toast.makeText(this, "Se han guardado los datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Se ha producido Un error", Toast.LENGTH_SHORT).show();
        }
    }

}