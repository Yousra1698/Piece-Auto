package com.example.gestionpieceauto.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gestionpieceauto.Produit.GestionPiece
import com.example.gestionpieceauto.databinding.ActivitySeConnecterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_inscription.*
import kotlinx.android.synthetic.main.activity_inscription.Email
import kotlinx.android.synthetic.main.activity_se_connecter.*

class SeConnecter : AppCompatActivity() {
       private lateinit var binding: ActivitySeConnecterBinding
        private lateinit var firebaseAuth: FirebaseAuth
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySeConnecterBinding.inflate(layoutInflater)
            setContentView(binding.root)
            firebaseAuth = FirebaseAuth.getInstance()
            binding.textView.setOnClickListener {
                val intent= Intent(this,Inscription::class.java)
                startActivity(intent)
            }
            binding.btnConnect.setOnClickListener {
                val email=binding.Email.text.toString()
                val pass=binding.PswdLogin.text.toString()
                if (email.isNotEmpty() && pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            binding.progressBar.visibility= View.VISIBLE
                                val intent = Intent(this,GestionPiece::class.java)
                                startActivity(intent)
                        }else{
                            Toast.makeText(this,"email ou mot de passe invalid", Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    this.Email.setError("Email et Oubligatoires!!")
                    this.PswdLogin.setError("Mot de passe et Oubligatoires!!")


                }
            }
        }

    }
