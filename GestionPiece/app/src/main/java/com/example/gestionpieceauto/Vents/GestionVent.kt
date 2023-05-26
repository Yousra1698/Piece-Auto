package com.example.gestionpieceauto.Vents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Adapter.AdapterVents
import com.example.gestionpieceauto.R
import com.google.firebase.database.*

class GestionVent : AppCompatActivity() {
    private lateinit var recyclerViewVente: RecyclerView
    private lateinit var refVente: DatabaseReference
    private lateinit var adapterVente: AdapterVents
    var listVente:ArrayList<Vent>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_vent)
        initialisationVues()
        selectVentes()
    }
    private fun initialisationVues() {
        val db= FirebaseDatabase.getInstance()
        this.refVente=db.getReference("Ventes")
        this.recyclerViewVente=this.findViewById(R.id.reyclerViewVente)
        listVente= ArrayList()
        this.adapterVente=AdapterVents(this@GestionVent,listVente!!)
        this.recyclerViewVente.layoutManager= LinearLayoutManager(this)
        this.recyclerViewVente.adapter=this.adapterVente
    }
    private fun selectVentes() {
        this.refVente.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listVente!!.clear()
                for (vente in snapshot!!.children){
                    var vente=vente.getValue(Vent::class.java)
                    listVente!!.add(vente!!)
                }
                adapterVente.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}