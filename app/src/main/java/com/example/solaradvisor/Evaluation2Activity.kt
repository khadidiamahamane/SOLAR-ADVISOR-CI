package com.example.solaradvisor

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Evaluation2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_evaluation2)

        val radioConsommation =
            findViewById<RadioButton>(R.id.radioConsommation)

        val radioFacture =
            findViewById<RadioButton>(R.id.radioFacture)

        val layoutConsommation =
            findViewById<LinearLayout>(R.id.layoutConsommation)

        val layoutFacture =
            findViewById<LinearLayout>(R.id.layoutFacture)

        val editConsommation =
            findViewById<EditText>(R.id.editConsommation)

        val editFacture =
            findViewById<EditText>(R.id.editFacture)

        val radioRarement =
            findViewById<RadioButton>(R.id.radioRarement)

        val radioParfois =
            findViewById<RadioButton>(R.id.radioParfois)

        val radioSouvent =
            findViewById<RadioButton>(R.id.radioSouvent)

        val btnContinuer =
            findViewById<Button>(R.id.btnContinuerEvaluation2)

        val btnRetour =
            findViewById<TextView>(R.id.btnRetourEvaluation2)


        radioConsommation.setOnClickListener {
            layoutConsommation.visibility = View.VISIBLE
            layoutFacture.visibility = View.GONE
            editFacture.text.clear()
        }

        radioFacture.setOnClickListener {
            layoutFacture.visibility = View.VISIBLE
            layoutConsommation.visibility = View.GONE
            editConsommation.text.clear()
        }


        btnContinuer.setOnClickListener {

            val consommationChoisie = radioConsommation.isChecked
            val factureChoisie = radioFacture.isChecked

            if (!consommationChoisie && !factureChoisie) {
                Toast.makeText(
                    this,
                    "Veuillez choisir une information.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (consommationChoisie &&
                editConsommation.text.toString().isEmpty()
            ) {
                editConsommation.error =
                    "Veuillez saisir votre consommation"
                return@setOnClickListener
            }

            if (factureChoisie &&
                editFacture.text.toString().isEmpty()
            ) {
                editFacture.error =
                    "Veuillez saisir votre facture"
                return@setOnClickListener
            }

            if (!radioRarement.isChecked &&
                !radioParfois.isChecked &&
                !radioSouvent.isChecked
            ) {
                Toast.makeText(
                    this,
                    "Veuillez indiquer la fréquence des coupures.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            // =========================
            // RÉCUPÉRER LES DONNÉES
            // =========================

            val consommation = if (consommationChoisie) {
                editConsommation.text.toString().toDouble()
            } else {
                0.0
            }

            val facture = if (factureChoisie) {
                editFacture.text.toString().toDouble()
            } else {
                0.0
            }

            val coupures = when {
                radioSouvent.isChecked -> "Souvent"
                radioParfois.isChecked -> "Parfois"
                else -> "Rarement"
            }


            // =========================
            // ENVOYER À L'ÉTAPE 3
            // =========================

            val intent =
                Intent(this, Evaluation3Activity::class.java)

            intent.putExtra("consommation", consommation)
            intent.putExtra("facture", facture)
            intent.putExtra("coupures", coupures)

            startActivity(intent)
        }


        btnRetour.setOnClickListener {
            finish()
        }
    }
}