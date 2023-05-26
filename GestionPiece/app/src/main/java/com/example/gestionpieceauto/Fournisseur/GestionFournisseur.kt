package com.example.gestionpieceauto.Fournisseur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Adapter.AdapterFournisseur
import com.example.gestionpieceauto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.add_fournisseur.view.*

class GestionFournisseur : AppCompatActivity() {
    private lateinit var addingBtn: FloatingActionButton
    private lateinit var RecyclerFournisseur: RecyclerView
    private lateinit var adapterFournisseur: AdapterFournisseur
    var ListFournisseur:ArrayList<Fournisseurs>?=null
    var Ref:DatabaseReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_fournisseur)
        ListFournisseur= ArrayList()
        val database= FirebaseDatabase.getInstance()
        Ref=database.getReference("Fournisseurs")
        RecyclerFournisseur=findViewById(R.id.RecyclerView_fournisseur)
        this.adapterFournisseur = AdapterFournisseur(this, ListFournisseur!!)
        this.RecyclerFournisseur.layoutManager=LinearLayoutManager(this@GestionFournisseur)
        this.RecyclerFournisseur.adapter= adapterFournisseur
        this.addingBtn =findViewById(R.id.actionbtn_adding_fournisseur)
        this.addingBtn.setOnClickListener {
            addInfo()
        }
    }
    override fun onStart() {
        super.onStart()
        Ref!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                ListFournisseur!!.clear()
                for (i in snapshot.children){
                    val fournisseur=i.getValue(Fournisseurs::class.java)
                    ListFournisseur!!.add(fournisseur!!)
                }
                adapterFournisseur.notifyDataSetChanged()
            }
        })
    }

    private fun addInfo() {
        val inflater= LayoutInflater.from(this)
        val view=inflater.inflate(R.layout.add_fournisseur,null)
        val alertBuilder= AlertDialog.Builder(this)
        alertBuilder.setView(view)
        val alertDialog=alertBuilder.create()
        alertDialog.show()
        view.findViewById<Button>(R.id.btnAjouterFournisseur).setOnClickListener {
            val nomComplet= view.edit_text_nomCompletFournisseur.text.toString()
            val nomSociete= view.edit_text_nomSocieteFournisseur.text.toString()
            val Email= view.edit_text_EmailFournisseur.text.toString()
            val Tel= view.edit_text_TelFournisseur.text.toString()
            if (nomComplet.isNotEmpty()&&nomSociete.isNotEmpty()&&Email.isNotEmpty()&&Tel.isNotEmpty()){
                var id= Ref!!.push().key
                var fourni=Fournisseurs(id!!,nomComplet,nomSociete,Email,Tel.toInt())
                Ref!!.child(id!!).setValue(fourni)
                alertDialog.dismiss()

            }else{
                Toast.makeText(this,"tous les champs oubligatoire", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
