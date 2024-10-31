package com.example.cuestionario_remastered

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var userFieldLogin = findViewById<EditText>(R.id.userFieldLogIn);
        var passwordFieldLogIn = findViewById<EditText>(R.id.passwordFieldLogIn);
        var btnLogIn = findViewById<Button>(R.id.btnLogIn);
        var btnSignIn = findViewById<Button>(R.id.btnSignIn);

        btnSignIn.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "users", null, 1);
            val db = admin.writableDatabase;
            val registry = ContentValues();
            registry.put("username", userFieldLogin.text.toString());
            registry.put("password", passwordFieldLogIn.text.toString());
            registry.put("score", 0.0);
            db.insert("users", null, registry);
            db.close();
            userFieldLogin.setText("");
            passwordFieldLogIn.setText("");
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
        }

        btnLogIn.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "users", null, 1);
            val db = admin.writableDatabase;
            val query = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", arrayOf(userFieldLogin.text.toString(), passwordFieldLogIn.text.toString()));
            if (query.moveToFirst()) {
                var intent = Intent(this, MainActivity::class.java);
                intent.putExtra("ID", query.getString(0));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Usuario no se ha encontrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
            query.close();
        }

    }
}