package com.example.gestionpieceauto.Produit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.gestionpieceauto.GestionCarburant
import com.example.gestionpieceauto.R

class GestionProduit : AppCompatActivity() {
    private lateinit var buttonGestionCarburant:ImageView
    private lateinit var buttonGestionHuile:ImageView
    private lateinit var buttonGestionCroix:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_produit)
        initialisation()
        this.buttonGestionCarburant.setOnClickListener {
            val i=Intent(this@GestionProduit,GestionCarburant::class.java)
            startActivity(i)
        }
       this.buttonGestionHuile.setOnClickListener{
            val gsHuil=Intent(this@GestionProduit, GestionHuile::class.java)
            startActivity(gsHuil)
       }
        this.buttonGestionCroix.setOnClickListener {
            val gsCroix=Intent(this@GestionProduit,GestionCroix::class.java)
            startActivity(gsCroix)
        }
        initialisation()
    }
    private fun initialisation(){
        this.buttonGestionCarburant=this.findViewById(R.id.carburant)
        this.buttonGestionHuile=this.findViewById(R.id.huile)
        this.buttonGestionCroix=this.findViewById(R.id.croix)
    }
}