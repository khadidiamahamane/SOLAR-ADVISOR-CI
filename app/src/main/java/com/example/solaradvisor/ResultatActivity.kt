package com.example.solaradvisor

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ResultatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultat)


        // =========================
        // RÉCUPÉRER LES RÉSULTATS
        // =========================

        val puissance =
            intent.getDoubleExtra("puissance", 0.0)

        val production =
            intent.getDoubleExtra("production", 0.0)

        val panneaux =
            intent.getIntExtra("panneaux", 0)

        val onduleur =
            intent.getDoubleExtra("onduleur", 0.0)

        val cout =
            intent.getDoubleExtra("cout", 0.0)


        // =========================
        // TEXTVIEWS
        // =========================

        val txtPuissance =
            findViewById<TextView>(R.id.txtPuissance)

        val txtProduction =
            findViewById<TextView>(R.id.txtProduction)

        val txtPanneaux =
            findViewById<TextView>(R.id.txtPanneaux)

        val txtOnduleur =
            findViewById<TextView>(R.id.txtOnduleur)

        val txtCout =
            findViewById<TextView>(R.id.txtCout)


        // =========================
        // AFFICHER LES RÉSULTATS
        // =========================

        txtPuissance.text =
            String.format(
                Locale.FRANCE,
                "%.1f kWc",
                puissance
            )

        txtProduction.text =
            String.format(
                Locale.FRANCE,
                "%.0f kWh / an",
                production
            )

        txtPanneaux.text =
            panneaux.toString()

        txtOnduleur.text =
            String.format(
                Locale.FRANCE,
                "%.1f kW",
                onduleur
            )

        txtCout.text =
            String.format(
                Locale.FRANCE,
                "%,.0f FCFA",
                cout
            )


        // =========================
        // RETOUR
        // =========================

        val btnRetour =
            findViewById<TextView>(R.id.btnRetourResultat)

        btnRetour.setOnClickListener {
            finish()
        }


        // =========================
        // FOURNISSEURS
        // =========================

        val btnFournisseurs =
            findViewById<Button>(R.id.btnFournisseurs)

        btnFournisseurs.setOnClickListener {

            val intent =
                Intent(
                    this,
                    FournisseursActivity::class.java
                )

            startActivity(intent)
        }


        // =========================
        // MENU BAS
        // =========================

        val navAccueil =
            findViewById<LinearLayout>(R.id.navAccueil)

        val navAnalyse =
            findViewById<LinearLayout>(R.id.navAnalyse)

        val navFournisseurs =
            findViewById<LinearLayout>(R.id.navFournisseurs)

        val navProfil =
            findViewById<LinearLayout>(R.id.navProfil)


        navAccueil.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    DashboardActivity::class.java
                )
            )
        }

        navAnalyse.setOnClickListener {
            // Déjà dans l'analyse
        }

        navFournisseurs.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    FournisseursActivity::class.java
                )
            )
        }

        navProfil.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ProfileActivity::class.java
                )
            )
        }
    }
}