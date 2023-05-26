package com.example.gestionpieceauto.Produit

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Achats.Achat
import com.example.gestionpieceauto.Adapter.AdapterCroix
import com.example.gestionpieceauto.DataClasses.Croix
import com.example.gestionpieceauto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class GestionCroix : AppCompatActivity() {
    private lateinit var flotingActionButtonAjouterCroix: FloatingActionButton
    private lateinit var refCroix: DatabaseReference
    private lateinit var recyclerCroix: RecyclerView
    private lateinit var adpterCroix: AdapterCroix
    private lateinit var listCroix: ArrayList<Croix>
    private lateinit var refAchats: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_croix)
        intialisationVues()
        referenceDataBase()
        this.flotingActionButtonAjouterCroix.setOnClickListener {
            alertDialogAjouterCroix()
        }
    }

    override fun onStart() {
        super.onStart()
        selectCroix()
    }

    private fun intialisationVues() {
        this.flotingActionButtonAjouterCroix=this.findViewById(R.id.floating_action_button_ajouter_croix)
        this.recyclerCroix=this.findViewById(R.id.list_croix)
        this.listCroix= ArrayList()
        this.adpterCroix= AdapterCroix(this@GestionCroix,listCroix!!)
        this.recyclerCroix.layoutManager= LinearLayoutManager(this@GestionCroix)
        this.recyclerCroix.adapter=this.adpterCroix
    }
    private fun referenceDataBase() {
        val dataBase= FirebaseDatabase.getInstance()
        this.refCroix=dataBase.getReference("Croix")
        this.refAchats=dataBase.getReference("Achats")
    }
    @SuppressLint("MissingInflatedId")
    private fun alertDialogAjouterCroix() {
        val alertBuilder= AlertDialog.Builder(this@GestionCroix)
        val view= LayoutInflater.from(this@GestionCroix).inflate(R.layout.ajoouter_croix,null)
        alertBuilder.setView(view)
        val alertDialog=alertBuilder.create()
        alertDialog.show()
        view.findViewById<Button>(R.id.button_ajouter_croix).setOnClickListener {

            var typeCroix=view.findViewById<EditText>(R.id.type_croix).text.toString()
            var prixAchatCroix=view.findViewById<EditText>(R.id.prix_achat_croix).text.toString()
            var prixVenteCroix=view.findViewById<EditText>(R.id.prix_vente_croix).text.toString()
            var dateAchatCroix=view.findViewById<EditText>(R.id.date_achat_croix).text.toString()
            var codeBarreCroix=view.findViewById<EditText>(R.id.code_barre_croix).text.toString()
            var VoitureCroix=view.findViewById<EditText>(R.id.voiture_croix).text.toString()
            var quantiteCroix=view.findViewById<EditText>(R.id.quantite_croix).text.toString()
            var fornisseurCroix=view.findViewById<EditText>(R.id.fornisseur_croix).text.toString()
            if(typeCroix.isNotBlank() && prixAchatCroix.isNotBlank() && prixVenteCroix.isNotBlank() && dateAchatCroix.isNotBlank() && codeBarreCroix.isNotBlank() && VoitureCroix.isNotBlank() && quantiteCroix.isNotBlank() && fornisseurCroix.isNotBlank()){
                var idRef=this.refCroix!!.push().key
                var idAchats=this.refAchats!!.push().key
                var croix=Croix(idRef!!,typeCroix,prixAchatCroix.toDouble(),prixVenteCroix.toDouble(),dateAchatCroix,codeBarreCroix.toInt(),quantiteCroix.toInt(),fornisseurCroix,VoitureCroix)
                val achat=Achat(idRef!!,typeCroix,quantiteCroix.toInt(),prixAchatCroix.toDouble())
                this.refAchats!!.child(idAchats!!).setValue(achat!!)
                this.refCroix.child(idRef!!).setValue(croix)
                Toast.makeText(this@GestionCroix,"Croix a ajouté en succès", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            }
            else{
                Toast.makeText(this@GestionCroix,"Rempli le formulaire", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun selectCroix() {
        this.refCroix.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listCroix!!.clear()
                for (croix in snapshot!!.children){
                    val croix=croix.getValue(Croix::class.java)!!
                    listCroix!!.add(croix!!)
                }
                adpterCroix.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        )
    }





}