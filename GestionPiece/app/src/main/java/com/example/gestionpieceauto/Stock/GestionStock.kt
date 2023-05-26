package com.example.gestionpieceauto.Stock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Adapter.AdapterStockCarburnat
import com.example.gestionpieceauto.Adapter.AdapterStockCroix
import com.example.gestionpieceauto.Adapter.AdapterStockHuile
import com.example.gestionpieceauto.DataClasses.Carburant
import com.example.gestionpieceauto.DataClasses.Croix
import com.example.gestionpieceauto.DataClasses.Huile
import com.example.gestionpieceauto.R
import com.google.firebase.database.*

class GestionStock : AppCompatActivity() {
    private lateinit var ref:DatabaseReference
    private lateinit var recyclerCarburant: RecyclerView
    private lateinit var adap: AdapterStockCarburnat
    private lateinit var refHuiles:DatabaseReference
    private lateinit var recyclerHuile: RecyclerView
    private lateinit var adapHuile: AdapterStockHuile
    private lateinit var refCroix:DatabaseReference
    private lateinit var recyclerCroix: RecyclerView
    private lateinit var adapCroix: AdapterStockCroix
    var listCarburant:ArrayList<Carburant>?=null
    var listHuile:ArrayList<Huile>?=null
    var listCroix:ArrayList<Croix>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gestion_stock)
        initViews()
        referenceDataBase()
        selectCarburants()
        selectHuiles()
        selectCroix()

    }
    private fun initViews() {
        this.recyclerCarburant=this.findViewById(R.id.stock_carburants)
        this.listCarburant=ArrayList()
        this.adap=AdapterStockCarburnat(this@GestionStock,listCarburant!!)
        this.recyclerCarburant.layoutManager= LinearLayoutManager(this)
        this.recyclerCarburant.adapter=this.adap
        this.recyclerHuile=this.findViewById(R.id.stock_huiles)
        this.listHuile=ArrayList()
        this.adapHuile=AdapterStockHuile(this@GestionStock,listHuile!!)
        this.recyclerHuile.layoutManager= LinearLayoutManager(this)
        this.recyclerHuile.adapter=this.adapHuile
        this.recyclerCroix=this.findViewById(R.id.stock_croix)
        this.listCroix=ArrayList()
        this.adapCroix=AdapterStockCroix(this@GestionStock,listCroix!!)
        this.recyclerCroix.layoutManager= LinearLayoutManager(this)
        this.recyclerCroix.adapter=this.adapCroix

    }
    private fun referenceDataBase() {
        var db=FirebaseDatabase.getInstance()
        this.ref=db.getReference("Carburants")
        this.refHuiles=db.getReference("Huiles")
        this.refCroix=db.getReference("Croix")

    }


    private fun selectCarburants() {
        this.ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (carburant in snapshot.children!!){
                    var carburant=carburant.getValue(Carburant::class.java)!!
                    listCarburant!!.add(carburant!!)
                }
                adap.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
    private fun selectHuiles() {
        this.refHuiles.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (huile in snapshot.children!!){
                    var huile=huile.getValue(Huile::class.java)!!
                    listHuile!!.add(huile!!)
                }
                adapHuile.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
    private fun selectCroix() {
        this.refCroix.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (croix in snapshot.children!!){
                    var croix=croix.getValue(Croix::class.java)!!
                    listCroix!!.add(croix!!)
                }
                adapCroix.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}