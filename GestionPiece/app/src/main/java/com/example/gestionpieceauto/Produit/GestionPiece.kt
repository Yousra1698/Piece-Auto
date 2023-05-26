package com.example.gestionpieceauto.Produit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.gestionpieceauto.Achats.GestionAchats
import com.example.gestionpieceauto.Client.GestionClient
import com.example.gestionpieceauto.Fournisseur.GestionFournisseur
import com.example.gestionpieceauto.Stock.GestionStock
import com.example.gestionpieceauto.R
import com.example.gestionpieceauto.Vents.GestionVent
import com.google.android.material.navigation.NavigationView

class GestionPiece : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var produit:CardView
    private lateinit var vents:CardView
    private lateinit var achat:CardView
    private lateinit var client:CardView
    private lateinit var fornisseur:CardView
    private lateinit var cardViewStock:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_piece)
        this.produit=this.findViewById(R.id.produit)
        this.client=this.findViewById(R.id.card_client)
        this.fornisseur=this.findViewById(R.id.card_fornisseur)
        this.cardViewStock=this.findViewById(R.id.card_view_stock)
        this.achat=this.findViewById(R.id.card_achat)
        this.vents=this.findViewById(R.id.card_vents)
        this.client.setOnClickListener {
            val client = Intent(this@GestionPiece, GestionClient::class.java)
            startActivity(client)
        }
        this.produit.setOnClickListener {
            val i =Intent(this@GestionPiece,GestionProduit::class.java)
            startActivity(i)
        }
        this.cardViewStock.setOnClickListener {
            val intent=Intent(this@GestionPiece,GestionStock::class.java)
            this.startActivity(intent)
        }
        this.fornisseur.setOnClickListener {
            val fornisseur=Intent(this@GestionPiece,GestionFournisseur::class.java)
            this.startActivity(fornisseur)
        }
        this.achat.setOnClickListener {
            val achts=Intent(this@GestionPiece,GestionAchats::class.java)
            this.startActivity(achts)
        }
        this.vents.setOnClickListener {
            val vents=Intent(this@GestionPiece,GestionVent::class.java)
            this.startActivity(vents)
        }
        toolbar=findViewById(R.id.toolbare)
        setSupportActionBar(toolbar)
        supportActionBar?.title="gestion Stock"
        drawerLayout = findViewById(R.id.drawerLayout)
        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.Open, R.string.Close)
        drawerLayout.addDrawerListener(actionBarToggle)
        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()
        navView = findViewById(R.id.navView)
        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Home -> {
                    var i=Intent(this,GestionPiece::class.java)
                    startActivity(i)
                    onBackPressed()
                    true
                }
                R.id.déconnecter -> {
                    finish()
                    onBackPressed()
                    true
                }
                else -> {
                    false
                }
            }
        }

    }




    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }
     //override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}

