package com.example.gestionpieceauto.Produit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Achats.Achat
import com.example.gestionpieceauto.Adapter.AdapterHuile
import com.example.gestionpieceauto.DataClasses.Huile
import com.example.gestionpieceauto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class GestionHuile : AppCompatActivity() {
    private lateinit var flotingActionButtonAjouterHuile: FloatingActionButton
    private lateinit var recyclerViewHuile: RecyclerView
    private lateinit var listHuiles:ArrayList<Huile>
    private lateinit var adapterHuile: AdapterHuile
    private lateinit var ref: DatabaseReference
    private lateinit var refAchats: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_huile)
        initViews()
        referenceDataBase()
        this.flotingActionButtonAjouterHuile.setOnClickListener {
            alertDialogAjouterHuile()
        }
    }

    override fun onStart() {
        super.onStart()
        selecHuiles()
    }
    private fun initViews() {
        this.flotingActionButtonAjouterHuile=this.findViewById(R.id.floating_action_button_ajouter_huile)
        this.recyclerViewHuile=this.findViewById(R.id.list_huiles)
        this.listHuiles=ArrayList<Huile>()
        this.adapterHuile= AdapterHuile(this,listHuiles)
        this.recyclerViewHuile.layoutManager= LinearLayoutManager(this)
        this.recyclerViewHuile.adapter=this.adapterHuile
    }
    private fun referenceDataBase() {
        val db= FirebaseDatabase.getInstance()
        this.ref=db.getReference("Huiles")
        this.refAchats=db.getReference("Achats")
    }

    private fun alertDialogAjouterHuile() {
        val alertBuilder= AlertDialog.Builder(this@GestionHuile)
        val view= LayoutInflater.from(this).inflate(R.layout.ajouter_huile,null)
        alertBuilder.setView(view)
        val alertDialog=alertBuilder.create()
        alertDialog.show()
        view.findViewById<Button>(R.id.button_ajouter_huile).setOnClickListener {
            var marqueHuile=view.findViewById<EditText>(R.id.marque_huile).text.toString()
            var typeHuile=view.findViewById<EditText>(R.id.type_huile).text.toString()
            var codeBarreHuile=view.findViewById<EditText>(R.id.code_barre_huile).text.toString()
            var prixAchatHuile=view.findViewById<EditText>(R.id.prix_achat_huile).text.toString()
            var dateAchatHuile=view.findViewById<EditText>(R.id.date_achat_huile).text.toString()
            var prixVenteHuile=view.findViewById<EditText>(R.id.prix_vente_huile).text.toString()
            var quantiteHuile=view.findViewById<EditText>(R.id.quantite_huile).text.toString()
            var fornisseurHuile=view.findViewById<EditText>(R.id.fornisseur_huile).text.toString()
            if(marqueHuile.isNotBlank() && typeHuile.isNotBlank() && codeBarreHuile.isNotBlank() && prixAchatHuile.isNotBlank() && prixVenteHuile.isNotBlank() && dateAchatHuile.isNotBlank() && typeHuile.isNotBlank() && quantiteHuile.isNotBlank() && fornisseurHuile.isNotBlank()){
                var idHuile=this.ref!!.push().key
                var idAchats=this.refAchats!!.push().key
                var huile= Huile(idHuile!!,marqueHuile,typeHuile,codeBarreHuile.toInt(),prixAchatHuile.toDouble(),dateAchatHuile,prixAchatHuile.toDouble(),quantiteHuile.toInt(),fornisseurHuile)
                val achat= Achat(idHuile!!,marqueHuile,quantiteHuile.toInt(),prixAchatHuile.toDouble())
                this.refAchats!!.child(idAchats!!).setValue(achat!!)
                this.ref.child(idHuile!!).setValue(huile)
                alertDialog.dismiss()
            }
            else{
                Toast.makeText(this@GestionHuile,"Erreur", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun selecHuiles(){
        val database= FirebaseDatabase.getInstance()
        this.ref=database.getReference("Huiles")
        this.ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listHuiles!!.clear()
                for(c in snapshot.children){
                    var huile: Huile =c.getValue(Huile::class.java)!!
                    listHuiles!!.add(huile!!)
                }
                adapterHuile.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}