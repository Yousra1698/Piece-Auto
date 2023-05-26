package com.example.gestionpieceauto.Client

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Adapter.AdapterClient
import com.example.gestionpieceauto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.add_clients.view.*

class GestionClient : AppCompatActivity() {
    private lateinit var addingBtn: FloatingActionButton
    private lateinit var Recycler_Client: RecyclerView
    private lateinit var adapterClient: AdapterClient
    var ListClient:ArrayList<Clients>?=null
    var Ref: DatabaseReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_client)
        ListClient= ArrayList()
        val database= FirebaseDatabase.getInstance()
        Ref=database.getReference("Clients")
        this.Recycler_Client =findViewById(R.id.recyclView_client)
        this.adapterClient = AdapterClient(this, ListClient!!)
        this.Recycler_Client.layoutManager= LinearLayoutManager(this@GestionClient)
        this.Recycler_Client.adapter= adapterClient
        this.addingBtn =findViewById(R.id.addingBtn_Client)
        this.addingBtn.setOnClickListener {
            addInfo()
        }
    }
    override fun onStart() {
        super.onStart()
        Ref!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                ListClient!!.clear()
                for (i in snapshot.children){
                    val client=i.getValue(Clients::class.java)
                    ListClient!!.add(client!!)
                }
                adapterClient.notifyDataSetChanged()
            }
        })
    }

    private fun addInfo() {
        val inflater= LayoutInflater.from(this)
        val view=inflater.inflate(R.layout.add_clients,null)
        val alertBuilder= AlertDialog.Builder(this)
        alertBuilder.setView(view)
        val alertDialog=alertBuilder.create()
        alertDialog.show()
        view.btnAjouter_Client.setOnClickListener {
            val nomComplet= view.nomComplet_Client.text.toString()
            val adresse= view.adresse_Client.text.toString()
            val Tel= view.tel_Client.text.toString()
            if (nomComplet.isNotEmpty()&&adresse.isNotEmpty()&&Tel.isNotEmpty()){
                var id= Ref!!.push().key
                var client=Clients(id!!,nomComplet,adresse,Tel)
                Ref!!.child(id!!).setValue(client)
                alertDialog.dismiss()

            }else{
                Toast.makeText(this,"tous les champs oubligatoire", Toast.LENGTH_SHORT).show()
            }
        }
    }
}