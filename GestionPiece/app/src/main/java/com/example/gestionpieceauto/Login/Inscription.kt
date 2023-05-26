package com.example.gestionpieceauto.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gestionpieceauto.Produit.GestionPiece
import com.example.gestionpieceauto.databinding.ActivityInscriptionBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_inscription.*
import kotlinx.android.synthetic.main.activity_inscription.Email
import kotlinx.android.synthetic.main.activity_se_connecter.*

class Inscription : AppCompatActivity() {
    private lateinit var binding: ActivityInscriptionBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textInscri.setOnClickListener {
            val intent= Intent(this,SeConnecter::class.java)
            startActivity(intent)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnInscr.setOnClickListener {
            val email = binding.Email.text.toString()
            val pass = binding.Pswd.text.toString()
            val confirmPass = binding.ComfPaswd.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, GestionPiece::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this,"email ou mot de passe invalid", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "le mot de passe ne correspond pas", Toast.LENGTH_SHORT).show()
                }
            } else {
                this.Email.setError("Email et Oubligatoires!")
                this.Pswd.setError("Mot de passe et Oubligatoires!")
                this.ComfPaswd.setError("Confirmation Mot de passe et Oubligatoires!")
            }
        }
    }
}
