package com.example.gestionpieceauto.Achats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Adapter.AdapterAchat
import com.example.gestionpieceauto.R
import com.google.firebase.database.*

class GestionAchats : AppCompatActivity() {
    private lateinit var recyclerViewAchats: RecyclerView
    private lateinit var adpterAchat: AdapterAchat
    private lateinit var refAchats: DatabaseReference
    var listAchats: ArrayList<Achat>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_achats)
        initViews()
        selectAchats()
    }

    private fun initViews() {
        val db = FirebaseDatabase.getInstance()
        this.refAchats = db.getReference("Achats")
        listAchats = ArrayList()
        this.recyclerViewAchats = this.findViewById(R.id.reyclerViewAchat)
        this.adpterAchat = AdapterAchat(this@GestionAchats, listAchats!!)
        this.recyclerViewAchats.layoutManager = LinearLayoutManager(this@GestionAchats)
        this.recyclerViewAchats.adapter = this.adpterAchat
        this.adpterAchat.notifyDataSetChanged()
    }

    private fun selectAchats() {
        this.refAchats.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listAchats!!.clear()
                for (achat in snapshot!!.children) {
                    var achat = achat.getValue(Achat::class.java)
                    listAchats!!.add(achat!!)
                }
                adpterAchat.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
