package com.example.solaradvisor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SolarTechActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_solar_tech)

        // =========================
        // BOUTON RETOUR
        // =========================

        val btnRetour = findViewById<TextView>(R.id.btnRetourSolarTech)

        btnRetour.setOnClickListener {
            finish()
        }


        // =========================
        // BOUTON CONTACT
        // =========================

        val btnContact = findViewById<Button>(R.id.btnEntrerContact)

        btnContact.setOnClickListener {

            // Pour le moment, on affiche simplement un message.
            // Le contact pourra être relié à WhatsApp ou à un appel plus tard.

            android.widget.Toast.makeText(
                this,
                "Contact avec SolarTech CI",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}