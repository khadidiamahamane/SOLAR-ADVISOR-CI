package com.example.solaradvisor

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Evaluation3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_evaluation3)

        // =========================
        // DONNÉES DE L'ÉTAPE 2
        // =========================

        val consommation =
            intent.getDoubleExtra("consommation", 0.0)

        val facture =
            intent.getDoubleExtra("facture", 0.0)

        val coupures =
            intent.getStringExtra("coupures") ?: "Rarement"


        // =========================
        // ÉQUIPEMENTS
        // =========================

        val checkClimatisation =
            findViewById<CheckBox>(R.id.checkClimatisation)

        val editClimatisation =
            findViewById<EditText>(R.id.editClimatisation)

        val checkEclairage =
            findViewById<CheckBox>(R.id.checkEclairage)

        val editEclairage =
            findViewById<EditText>(R.id.editEclairage)

        val checkRefrigeration =
            findViewById<CheckBox>(R.id.checkRefrigeration)

        val editRefrigeration =
            findViewById<EditText>(R.id.editRefrigeration)

        val checkOrdinateurs =
            findViewById<CheckBox>(R.id.checkOrdinateurs)

        val editOrdinateurs =
            findViewById<EditText>(R.id.editOrdinateurs)

        val checkPompes =
            findViewById<CheckBox>(R.id.checkPompes)

        val editPompes =
            findViewById<EditText>(R.id.editPompes)

        val checkAutre =
            findViewById<CheckBox>(R.id.checkAutre)

        val editAutre =
            findViewById<EditText>(R.id.editAutre)


        // =========================
        // AFFICHER / CACHER
        // =========================

        checkClimatisation.setOnCheckedChangeListener { _, checked ->
            editClimatisation.visibility =
                if (checked) View.VISIBLE else View.GONE
        }

        checkEclairage.setOnCheckedChangeListener { _, checked ->
            editEclairage.visibility =
                if (checked) View.VISIBLE else View.GONE
        }

        checkRefrigeration.setOnCheckedChangeListener { _, checked ->
            editRefrigeration.visibility =
                if (checked) View.VISIBLE else View.GONE
        }

        checkOrdinateurs.setOnCheckedChangeListener { _, checked ->
            editOrdinateurs.visibility =
                if (checked) View.VISIBLE else View.GONE
        }

        checkPompes.setOnCheckedChangeListener { _, checked ->
            editPompes.visibility =
                if (checked) View.VISIBLE else View.GONE
        }

        checkAutre.setOnCheckedChangeListener { _, checked ->
            editAutre.visibility =
                if (checked) View.VISIBLE else View.GONE
        }


        val radioGroupDuree =
            findViewById<RadioGroup>(R.id.radioGroupDuree)


        // =========================
        // CONTINUER
        // =========================

        val btnContinuer =
            findViewById<Button>(R.id.btnContinuerEvaluation3)

        btnContinuer.setOnClickListener {

            if (!checkClimatisation.isChecked &&
                !checkEclairage.isChecked &&
                !checkRefrigeration.isChecked &&
                !checkOrdinateurs.isChecked &&
                !checkPompes.isChecked &&
                !checkAutre.isChecked
            ) {
                Toast.makeText(
                    this,
                    "Veuillez sélectionner au moins un équipement.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (radioGroupDuree.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Veuillez sélectionner une durée d'utilisation.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            // Vérification des quantités

            if (checkClimatisation.isChecked &&
                editClimatisation.text.toString().isEmpty()
            ) {
                editClimatisation.error = "Indiquez la quantité"
                return@setOnClickListener
            }

            if (checkEclairage.isChecked &&
                editEclairage.text.toString().isEmpty()
            ) {
                editEclairage.error = "Indiquez la quantité"
                return@setOnClickListener
            }

            if (checkRefrigeration.isChecked &&
                editRefrigeration.text.toString().isEmpty()
            ) {
                editRefrigeration.error = "Indiquez la quantité"
                return@setOnClickListener
            }

            if (checkOrdinateurs.isChecked &&
                editOrdinateurs.text.toString().isEmpty()
            ) {
                editOrdinateurs.error = "Indiquez la quantité"
                return@setOnClickListener
            }

            if (checkPompes.isChecked &&
                editPompes.text.toString().isEmpty()
            ) {
                editPompes.error = "Indiquez la quantité"
                return@setOnClickListener
            }


            // =========================
            // CALCUL DU NOMBRE TOTAL
            // D'ÉQUIPEMENTS
            // =========================

            var nombreEquipements = 0

            if (checkClimatisation.isChecked)
                nombreEquipements +=
                    editClimatisation.text.toString().toInt()

            if (checkEclairage.isChecked)
                nombreEquipements +=
                    editEclairage.text.toString().toInt()

            if (checkRefrigeration.isChecked)
                nombreEquipements +=
                    editRefrigeration.text.toString().toInt()

            if (checkOrdinateurs.isChecked)
                nombreEquipements +=
                    editOrdinateurs.text.toString().toInt()

            if (checkPompes.isChecked)
                nombreEquipements +=
                    editPompes.text.toString().toInt()


            // =========================
            // DURÉE
            // =========================

            val duree = when (radioGroupDuree.checkedRadioButtonId) {

                R.id.radioMoins4 -> 3.0

                R.id.radio4a8 -> 6.0

                R.id.radioPlus8 -> 10.0

                else -> 6.0
            }


            // =========================
            // ENVOYER À L'ÉTAPE 4
            // =========================

            val nextIntent =
                Intent(this, Evaluation4Activity::class.java)

            nextIntent.putExtra(
                "consommation",
                consommation
            )

            nextIntent.putExtra(
                "facture",
                facture
            )

            nextIntent.putExtra(
                "coupures",
                coupures
            )

            nextIntent.putExtra(
                "nombreEquipements",
                nombreEquipements
            )

            nextIntent.putExtra(
                "duree",
                duree
            )

            startActivity(nextIntent)
        }


        val btnRetour =
            findViewById<TextView>(R.id.btnRetourEvaluation3)

        btnRetour.setOnClickListener {
            finish()
        }
    }
}