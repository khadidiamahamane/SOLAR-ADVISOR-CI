package com.example.solaradvisor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // Vérifier si un utilisateur est déjà connecté
        if (auth.currentUser != null) {

            // Utilisateur déjà connecté
            startActivity(
                Intent(this, DashboardActivity::class.java)
            )

        } else {

            // Aucun utilisateur connecté
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

        finish()
    }
}