package com.example.solaradvisor

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.content.Intent
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EvaluationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_evaluation)

        val btnContinuerEvaluation =
            findViewById<Button>(R.id.btnContinuerEvaluation)

        val btnRetourEvaluation =
            findViewById<TextView>(R.id.btnRetour)

        btnRetourEvaluation.setOnClickListener {
            finish()
        }

        btnContinuerEvaluation.setOnClickListener {

            val radioGroup =
                findViewById<RadioGroup>(R.id.radioGroupStructure)

            if (radioGroup.checkedRadioButtonId == -1) {

                Toast.makeText(
                    this,
                    "Veuillez sélectionner votre type de structure.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(
                this,
                Evaluation2Activity::class.java
            )

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
            // On est déjà dans l'analyse
        }

        navFournisseurs.setOnClickListener {
            startActivity(Intent(this, FournisseursActivity::class.java))
        }

        navProfil.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

}