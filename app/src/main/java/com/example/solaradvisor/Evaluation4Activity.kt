package com.example.solaradvisor

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class Evaluation4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_evaluation4)

        // =========================
        // DONNÉES DES ÉTAPES PRÉCÉDENTES
        // =========================

        val consommation =
            intent.getDoubleExtra("consommation", 0.0)

        val facture =
            intent.getDoubleExtra("facture", 0.0)

        val coupures =
            intent.getStringExtra("coupures") ?: "Rarement"

        val nombreEquipements =
            intent.getIntExtra("nombreEquipements", 0)

        val duree =
            intent.getDoubleExtra("duree", 6.0)


        // =========================
        // GROUPES DE QUESTIONS
        // =========================

        val radioGroupObjectif =
            findViewById<RadioGroup>(R.id.radioGroupObjectif)

        val radioGroupBudget =
            findViewById<RadioGroup>(R.id.radioGroupBudget)

        val radioGroupDelai =
            findViewById<RadioGroup>(R.id.radioGroupDelai)


        val btnVoirEstimation =
            findViewById<Button>(R.id.btnVoirEstimation)


        btnVoirEstimation.setOnClickListener {

            // =========================
            // VALIDATIONS
            // =========================

            if (radioGroupObjectif.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Veuillez sélectionner votre objectif.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (radioGroupBudget.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Veuillez sélectionner votre budget.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (radioGroupDelai.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Veuillez sélectionner un délai.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            // =========================
            // CALCUL
            // =========================

            var consommationFinale = consommation

            /*
             Si l'utilisateur a donné uniquement
             sa facture, on fait une estimation
             simple de sa consommation.
            */

            if (consommationFinale <= 0 && facture > 0) {

                // estimation indicative :
                // 1 kWh ≈ 100 FCFA

                consommationFinale =
                    facture / 100.0
            }


            // =========================
            // PUISSANCE SOLAIRE
            // =========================

            /*
             Environ 4 heures solaires utiles/jour.
             On ajoute une petite marge.
            */

            val puissance =
                if (consommationFinale > 0) {

                    consommationFinale /
                            (4.0 * 30.0 * 0.80)

                } else {

                    // si aucune donnée numérique
                    // on utilise les équipements

                    nombreEquipements * 0.5
                }


            // =========================
            // NOMBRE DE PANNEAUX
            // =========================

            // panneau de 550 W

            val nombrePanneaux =
                ceil(puissance / 0.55).toInt()


            // =========================
            // ONDULEUR
            // =========================

            val puissanceOnduleur =
                puissance * 0.9


            // =========================
            // PRODUCTION ANNUELLE
            // =========================

            val productionAnnuelle =
                puissance * 4.0 * 365.0 * 0.80


            // =========================
            // COÛT ESTIMATIF
            // =========================

            /*
             Estimation simple pour la démonstration :
             environ 850 000 FCFA par kWc installé.
            */

            val coutInstallation =
                puissance * 850000


            // =========================
            // OUVRIR LES RÉSULTATS
            // =========================

            val intent =
                Intent(this, ResultatActivity::class.java)

            intent.putExtra(
                "puissance",
                puissance
            )

            intent.putExtra(
                "production",
                productionAnnuelle
            )

            intent.putExtra(
                "panneaux",
                nombrePanneaux
            )

            intent.putExtra(
                "onduleur",
                puissanceOnduleur
            )

            intent.putExtra(
                "cout",
                coutInstallation
            )

            intent.putExtra(
                "consommation",
                consommationFinale
            )

            intent.putExtra(
                "coupures",
                coupures
            )

            startActivity(intent)
        }


        // =========================
        // RETOUR
        // =========================

        val btnRetour =
            findViewById<TextView>(R.id.btnRetourEvaluation4)

        btnRetour.setOnClickListener {
            finish()
        }
    }
}