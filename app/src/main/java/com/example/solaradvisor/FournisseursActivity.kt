package com.example.solaradvisor


import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.widget.TextView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class FournisseursActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fournisseurs)

        val cardSolarTech = findViewById<LinearLayout>(R.id.cardSolarTech)

        cardSolarTech.setOnClickListener {
           val intent = Intent(this, SolarTechActivity::class.java)
           startActivity(intent)
        }

        val navAccueil = findViewById<LinearLayout>(R.id.navAccueil)
        val navAnalyse = findViewById<LinearLayout>(R.id.navAnalyse)
        val navFournisseurs = findViewById<LinearLayout>(R.id.navFournisseurs)
        val navProfil = findViewById<LinearLayout>(R.id.navProfil)

        navAccueil.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        navAnalyse.setOnClickListener {
            startActivity(Intent(this, EvaluationActivity::class.java))
        }

        navFournisseurs.setOnClickListener {
            // On est déjà sur Fournisseurs
        }

        navProfil.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}