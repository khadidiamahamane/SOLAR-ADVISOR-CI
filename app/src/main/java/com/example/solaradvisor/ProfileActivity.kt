package com.example.solaradvisor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

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
            startActivity(Intent(this, FournisseursActivity::class.java))
        }

        navProfil.setOnClickListener {
            // On est déjà sur profil
        }

        // =========================
        // FIREBASE
        // =========================

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


        // =========================
        // ELEMENTS DU PROFIL
        // =========================

        val txtInitiale =
            findViewById<TextView>(R.id.txtInitiale)

        val txtNomStructure =
            findViewById<TextView>(R.id.txtNomStructure)

        val txtTypeStructure =
            findViewById<TextView>(R.id.txtTypeStructure)

        val txtEmail =
            findViewById<TextView>(R.id.txtEmail)

        val txtTelephone =
            findViewById<TextView>(R.id.txtTelephone)

        val btnDeconnexion =
            findViewById<Button>(R.id.btnDeconnexion)


        // =========================
        // UTILISATEUR CONNECTÉ
        // =========================

        val currentUser = auth.currentUser

        if (currentUser == null) {

            // Aucun utilisateur connecté
            val intent =
                Intent(this, LoginActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()

            return
        }


        // Email provenant de Firebase Authentication

        txtEmail.text =
            currentUser.email ?: ""


        // =========================
        // RECUPERATION FIRESTORE
        // =========================

        val uid = currentUser.uid

        db.collection("utilisateurs")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    val nomStructure =
                        document.getString("nomStructure") ?: ""

                    val typeStructure =
                        document.getString("typeStructure") ?: ""

                    val telephone =
                        document.getString("telephone") ?: ""


                    // Nom de la structure

                    txtNomStructure.text =
                        nomStructure


                    // Type de structure

                    txtTypeStructure.text =
                        "Type : $typeStructure"


                    // Téléphone

                    txtTelephone.text =
                        "Téléphone : $telephone"


                    // =========================
                    // INITIALE
                    // =========================

                    if (nomStructure.isNotEmpty()) {

                        val initiale =
                            nomStructure.trim()
                                .first()
                                .uppercaseChar()

                        txtInitiale.text =
                            initiale.toString()

                    } else {

                        txtInitiale.text = "?"
                    }

                } else {

                    Toast.makeText(
                        this,
                        "Informations du profil introuvables.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "Impossible de récupérer les informations du profil.",
                    Toast.LENGTH_SHORT
                ).show()
            }


        // =========================
        // DECONNEXION
        // =========================

        btnDeconnexion.setOnClickListener {

            auth.signOut()

            Toast.makeText(
                this,
                "Vous êtes déconnecté.",
                Toast.LENGTH_SHORT
            ).show()

            val intent =
                Intent(this, LoginActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }
}