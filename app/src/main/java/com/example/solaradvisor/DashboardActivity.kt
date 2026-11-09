package com.example.solaradvisor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        // =========================
        // GESTION DES MARGES SYSTÈME
        // =========================

        val scrollView =
            findViewById<ScrollView>(R.id.scrollViewDashboard)

        ViewCompat.setOnApplyWindowInsetsListener(scrollView) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                view.paddingLeft,
                systemBars.top,
                view.paddingRight,
                systemBars.bottom
            )

            insets
        }


        // =========================
        // BOUTON COMMENCER L'ÉVALUATION
        // =========================

        val btnCommencerEvaluation =
            findViewById<Button>(R.id.btnCommencerEvaluation)

        btnCommencerEvaluation.setOnClickListener {

            val intent = Intent(
                this,
                EvaluationActivity::class.java
            )

            startActivity(intent)
        }


        // =========================
        // MENU DU BAS
        // =========================

        val navAccueil =
            findViewById<LinearLayout>(R.id.navAccueil)

        val navAnalyse =
            findViewById<LinearLayout>(R.id.navAnalyse)

        val navFournisseurs =
            findViewById<LinearLayout>(R.id.navFournisseurs)

        val navProfil =
            findViewById<LinearLayout>(R.id.navProfil)


        // =========================
        // ACCUEIL
        // =========================

        navAccueil.setOnClickListener {

            // On est déjà sur le Dashboard
        }


        // =========================
        // ANALYSE
        // =========================

        navAnalyse.setOnClickListener {

            val intent = Intent(
                this,
                EvaluationActivity::class.java
            )

            startActivity(intent)
        }


        // =========================
        // FOURNISSEURS
        // =========================

        navFournisseurs.setOnClickListener {

            val intent = Intent(
                this,
                FournisseursActivity::class.java
            )

            startActivity(intent)
        }


        // =========================
        // PROFIL
        // =========================

        navProfil.setOnClickListener {

            val intent = Intent(
                this,
                ProfileActivity::class.java
            )

            startActivity(intent)
        }
    }
}