package com.example.solaradvisor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Champs du formulaire
        val edtEmail =
            findViewById<EditText>(R.id.edtEmail)

        val edtPassword =
            findViewById<EditText>(R.id.edtPassword)

        val btnConnexion =
            findViewById<Button>(R.id.btnConnexion)

        val txtInscription =
            findViewById<TextView>(R.id.txtInscription)


        // =========================
        // CONNEXION
        // =========================

        btnConnexion.setOnClickListener {

            val email =
                edtEmail.text.toString().trim()

            val password =
                edtPassword.text.toString()


            // Vérification des champs
            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Veuillez remplir tous les champs.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }


            // Connexion Firebase
            auth.signInWithEmailAndPassword(
                email,
                password
            )
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Connexion réussie !",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent =
                            Intent(this, DashboardActivity::class.java)

                        startActivity(intent)

                        finish()

                    } else {

                        Toast.makeText(
                            this,
                            "Email ou mot de passe incorrect.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }


        // =========================
        // ALLER À L'INSCRIPTION
        // =========================

        txtInscription.setOnClickListener {

            val intent =
                Intent(this, RegisterActivity::class.java)

            startActivity(intent)
        }
    }
}