package com.example.cuestionario_remastered

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

        var bundle = intent.extras;
        var id = bundle?.getString("ID")

        fun retrieveDataUsername(id: String): String {
            val admin = AdminSQLiteOpenHelper(this, "users", null, 1);
            val db = admin.readableDatabase;
            var user = "usuario no disponible"
            val query = db.rawQuery("SELECT * FROM users WHERE id = ?", arrayOf(id));
            if (query.moveToFirst()) {
                user = query.getString(1);
            }
            query.close();
            db.close();

            return user
        }

        fun retrieveDataScore(id: String): String {
            val admin = AdminSQLiteOpenHelper(this, "users", null, 1);
            val db = admin.readableDatabase;
            var score = "XD"
            val query = db.rawQuery("SELECT * FROM users WHERE id = ?", arrayOf(id));
            if (query.moveToFirst()) {
                score = query.getString(3);
            }
            db.close()
            query.close()

            return score;
        }

        var testLabel = findViewById<TextView>(R.id.testLabel);
        var usernameLabel = retrieveDataUsername(id.toString());
        var scoreuser = retrieveDataScore(id.toString());
        var labelScore = findViewById<TextView>(R.id.labelScore);
        var btnComenzar = findViewById<Button>(R.id.btnComenzarCuestionario);

        testLabel.setText(usernameLabel);
        labelScore.setText(scoreuser);

        btnComenzar.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "users", null, 1);
            val db = admin.readableDatabase;
            var query =  db.rawQuery("SELECT * FROM users WHERE id = ?", arrayOf(id))
            var intent = Intent(this, Pregunta1::class.java)
            if (query.moveToFirst()) {
                intent.putExtra("ID", query.getString(0));
            }
            startActivity(intent);
            db.close();
            query.close();
        }

    }
}