package com.example.gestionpieceauto.Vents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gestionpieceauto.Login.Inscription
import com.example.gestionpieceauto.R
import com.example.gestionpieceauto.Login.SeConnecter

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnConnecter.setOnClickListener {
            val i=Intent(this, SeConnecter::class.java)
            startActivity(i)
        }
        btnInscription.setOnClickListener {
            val inscri=Intent(this, Inscription::class.java)
            startActivity(inscri)
        }

    }
}