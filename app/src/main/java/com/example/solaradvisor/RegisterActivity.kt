package com.example.solaradvisor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Récupération des éléments du formulaire
        val edtNomStructure =
            findViewById<EditText>(R.id.edtNomStructure)

        val spinnerTypeStructure =
            findViewById<Spinner>(R.id.spinnerTypeStructure)


        val edtEmailRegister =
            findViewById<EditText>(R.id.edtEmailRegister)

        val edtTelephone =
            findViewById<EditText>(R.id.edtTelephone)

        val edtPasswordRegister =
            findViewById<EditText>(R.id.edtPasswordRegister)

        val edtConfirmPassword =
            findViewById<EditText>(R.id.edtConfirmPassword)

        val btnCreerCompte =
            findViewById<Button>(R.id.btnCreerCompte)

        val txtRetourConnexion =
            findViewById<TextView>(R.id.txtRetourConnexion)


        // ==============================
        // BOUTON CRÉER MON COMPTE
        // ==============================

        btnCreerCompte.setOnClickListener {

            val nomStructure =
                edtNomStructure.text.toString().trim()

            val typeStructure =
                spinnerTypeStructure.selectedItem?.toString()?.trim() ?: ""

            val email =
                edtEmailRegister.text.toString().trim()

            val telephone =
                edtTelephone.text.toString().trim()

            val password =
                edtPasswordRegister.text.toString()

            val confirmPassword =
                edtConfirmPassword.text.toString()


            // Vérification des champs
            if (nomStructure.isEmpty() ||
                email.isEmpty() ||
                telephone.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Veuillez remplir tous les champs.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Vérification type de structure
            if (typeStructure == "Choisir un type de structure") {

                Toast.makeText(
                    this,
                    "Veuillez sélectionner un type de structure.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }


            // Vérification du mot de passe
            if (password != confirmPassword) {

                Toast.makeText(
                    this,
                    "Les mots de passe ne correspondent pas.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }


            // Création du compte Firebase
            auth.createUserWithEmailAndPassword(
                email,
                password
            )
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        val user = auth.currentUser

                        if (user != null) {

                            val utilisateur = hashMapOf(
                                "nomStructure" to nomStructure,
                                "typeStructure" to typeStructure,
                                "telephone" to telephone,
                                "email" to email
                            )

                            db.collection("utilisateurs")
                                .document(user.uid)
                                .set(utilisateur)
                                .addOnSuccessListener {

                                    Toast.makeText(
                                        this,
                                        "Compte créé avec succès !",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(
                                        this,
                                        DashboardActivity::class.java
                                    )

                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener {

                                    Toast.makeText(
                                        this,
                                        "Compte créé, mais impossible d'enregistrer vos informations.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        }

                    } else {

                        Toast.makeText(
                            this,
                            "Erreur : ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


        // ==============================
        // RETOUR VERS CONNEXION
        // ==============================

        txtRetourConnexion.setOnClickListener {

            val intent =
                Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }
    }
}