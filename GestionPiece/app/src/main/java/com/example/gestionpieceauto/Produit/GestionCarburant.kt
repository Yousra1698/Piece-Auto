package com.example.gestionpieceauto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Achats.Achat
import com.example.gestionpieceauto.Achats.GestionAchats
import com.example.gestionpieceauto.Adapter.AdapterCarburant
import com.example.gestionpieceauto.DataClasses.Carburant
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.list_achats.*

class GestionCarburant : AppCompatActivity() {
    private lateinit var floatinActionButtonAjouterCarburant: FloatingActionButton
    private lateinit var lisViewCarburant: RecyclerView
    private lateinit var adapterCarburant: AdapterCarburant
    private lateinit var listCarburants:ArrayList<Carburant>
    private lateinit var ref: DatabaseReference
    private lateinit var refAchats: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_carburant)
        initialisationVues()
        referenceDataBase()
        //modifierCarburant()
        this.floatinActionButtonAjouterCarburant.setOnClickListener {
            creerAlertDialogAjouterCarburant()
        }

    }

    override fun onStart() {
        super.onStart()
      selecCarburants()
    }
    private fun initialisationVues() {
        this.floatinActionButtonAjouterCarburant=this.findViewById(R.id.floating_action_button_ajouter_carburant)
        this.lisViewCarburant=this.findViewById(R.id.list_view_carburants)
        this.listCarburants=ArrayList<Carburant>()
        this.adapterCarburant= AdapterCarburant(this,listCarburants)
        this.lisViewCarburant.layoutManager=LinearLayoutManager(this)
        this.lisViewCarburant.adapter=this.adapterCarburant


    }
    private fun referenceDataBase() {
        val database= FirebaseDatabase.getInstance()
        this.ref=database.getReference("Carburants")
        this.refAchats=database.getReference("Achats")
    }
    private fun creerAlertDialogAjouterCarburant(){
        val alertBuilder= AlertDialog.Builder(this@GestionCarburant)
        val view=layoutInflater.inflate(R.layout.ajouter_carburant,null)
        alertBuilder.setView(view)
        val alertDialog=alertBuilder.create()
        alertDialog.show()
        view.findViewById<Button>(R.id.button_ajouter_carburant).setOnClickListener {
            var codeBarreCarburant=view.findViewById<EditText>(R.id.code_barre_carburant).text.toString()
            var designationCarburant=view.findViewById<EditText>(R.id.designation_carburant).text.toString()
            var prixAchatCarburant=view.findViewById<EditText>(R.id.prix_achat_carburant).text.toString()
            var dateAchatCarburant=view.findViewById<EditText>(R.id.date_achat_carburant).text.toString()
            var prixVenteCarburant=view.findViewById<EditText>(R.id.prix_vente_carburant).text.toString()
            var typeCarburant=view.findViewById<EditText>(R.id.type_carburant).text.toString()
            var quantiteCarburant=view.findViewById<EditText>(R.id.quantite_carburant).text.toString()
            var fornisseurCarburant=view.findViewById<EditText>(R.id.fornisseur_carburant).text.toString()

            if(codeBarreCarburant.isNotBlank() && designationCarburant.isNotBlank() && prixAchatCarburant.isNotBlank() && dateAchatCarburant.isNotBlank() && prixVenteCarburant.isNotBlank() && typeCarburant.isNotBlank() && quantiteCarburant.isNotBlank() ){
                var id=this.ref!!.push().key
                var idAchats=this.refAchats!!.push().key
                val carburant= Carburant(id!!,designationCarburant,prixAchatCarburant.toDouble(),dateAchatCarburant, prixVenteCarburant.toDouble(),codeBarreCarburant.toInt(),typeCarburant,quantiteCarburant.toInt(),fornisseurCarburant)
                val achat=Achat(id!!,designationCarburant,quantiteCarburant.toInt(),prixAchatCarburant.toDouble())
                this.ref!!.child(id).setValue(carburant)
                this.refAchats!!.child(idAchats!!).setValue(achat!!)
                alertDialog.dismiss()
                Toast.makeText(this@GestionCarburant,"Bien", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@GestionCarburant,"Rempli le formulaire", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun selecCarburants(){
        val database= FirebaseDatabase.getInstance()
        this.ref=database.getReference("Carburants")
        this.ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listCarburants!!.clear()
                for(c in snapshot.children){
                    var carburant: Carburant =c.getValue(Carburant::class.java)!!
                    listCarburants!!.add(carburant!!)
                }
                adapterCarburant.notifyDataSetChanged()
           }

           override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
//private fun modifierCarburant() {
//    this.lisViewCarburant.onItemLongClickListener =
//          AdapterView.OnItemLongClickListener { parent, view, position, id ->
//
//              var carburant = listCarburants?.get(position)!!
//              view.findViewById<EditText>(R.id.edit_text_nov_designation_carburant)
//                  .setText(carburant.designation.toString())
//              view.findViewById<EditText>(R.id.edit_text_nov_prix_achat_carburant)
//                  .setText(carburant.prixAchat.toString())
//              view.findViewById<EditText>(R.id.edit_text_nov_prix_vente_carburant)
//                  .setText(carburant.prixVente.toString())
//              view.findViewById<EditText>(R.id.edit_text_nov_type_carburant)
//                  .setText(carburant.type.toString())
//
//              view.findViewById<Button>(R.id.button_modifier_carburant).setOnClickListener {
//
//                  var carburantModifier = Carburant(
//                      carburant.id!!,
//                      novDesignation,
//                      novPrixAchat.toDouble(),
//                      novPrixVente.toDouble(),
//                      novType
//                  )
//
//                  childRef.setValue(carburantModifier)
//                  alertDialog.dismiss()
//              }
//              view.findViewById<Button>(R.id.button_supprimer_carburant).setOnClickListener {
//                  this.ref.child(carburant.id!!).removeValue()
//                  alertDialog.dismiss()
//              }
//              false
//          }*/
//} }
//
