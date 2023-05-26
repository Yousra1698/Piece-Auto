package com.example.gestionpieceauto.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.DataClasses.Carburant
import com.example.gestionpieceauto.R
import com.example.gestionpieceauto.Vents.Vent
import com.google.firebase.database.FirebaseDatabase

class AdapterCarburant(val context: Context,val listCarburant: ArrayList<Carburant>):RecyclerView.Adapter<AdapterCarburant.CarburantViewHolder> (){
    inner class CarburantViewHolder(val v:View): RecyclerView.ViewHolder(v){
        var codeBarre: TextView
        var designation: TextView
        var prixAchat: TextView
        var menu: ImageView
        init {
           codeBarre =v.findViewById<TextView>(R.id.CodeBarre)
            designation=v.findViewById<TextView>(R.id.designation)
           prixAchat=v.findViewById<TextView>(R.id.prixAchat)
            menu=v.findViewById(R.id.menu)
            menu.setOnClickListener { popupMenu(it) }
        }
        @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
        private fun popupMenu(v: View) {
            val popupMenu=PopupMenu(context,v)
            popupMenu.inflate(R.menu.show_menu)
            val carburant=listCarburant[adapterPosition]
            val database=FirebaseDatabase.getInstance()
            var ref=database.getReference("Carburants")
            var refVente=database.getReference("Ventes")
            popupMenu.setOnMenuItemClickListener {
              when (it.itemId){
                  R.id.modifier ->{
                      var view = LayoutInflater.from(context).inflate(R.layout.modifier_carburant, null)
                      var alertBuilder=AlertDialog.Builder(context)
                      alertBuilder.setView(view)
                      val alertDialog=alertBuilder.create()
                      alertDialog.show()
                      view.findViewById<EditText>(R.id.modifier_code_barre_carburant).text.toString()
                      view.findViewById<EditText>(R.id.modifier_designation_carburant).text.toString()
                      view.findViewById<EditText>(R.id.modifier_prix_achat_carburant).text.toString()
                      view.findViewById<EditText>(R.id.modifier_date_achat_carburant).text.toString()
                      view.findViewById<EditText>(R.id.modifier_prix_vente_carburant).text.toString()
                      view.findViewById<EditText>(R.id.modifier_type_carburant).text.toString()
                      view.findViewById<EditText>(R.id.modifier_quantite_carburant).text.toString()
                      view.findViewById<Button>(R.id.button_modifier_carburant).setOnClickListener {
                      var novCodeBarre= view.findViewById<EditText>(R.id.modifier_code_barre_carburant).text.toString()
                      var novDesignation = view.findViewById<EditText>(R.id.modifier_designation_carburant).text.toString()
                      var novPrixAchat = view.findViewById<EditText>(R.id.modifier_prix_achat_carburant).text.toString()
                      var novDateAchat = view.findViewById<EditText>(R.id.modifier_date_achat_carburant).text.toString()
                      var novPrixVente = view.findViewById<EditText>(R.id.modifier_prix_vente_carburant).text.toString()
                      var novType = view.findViewById<EditText>(R.id.modifier_type_carburant).text.toString()
                      var novQuantite = view.findViewById<EditText>(R.id.modifier_quantite_carburant).text.toString()
                      var childRef = ref?.child(carburant.id.toString())
                          if (novCodeBarre.isNotEmpty()&&novDesignation.isNotEmpty()&&novPrixAchat.isNotEmpty()&&novDateAchat.isNotEmpty()&&novPrixVente.isNotEmpty()&&novType.isNotEmpty()&&novQuantite.isNotEmpty()){
                              var carburantModifier = Carburant(carburant.id!!, novDesignation,novPrixAchat.toDouble(),novDateAchat,novPrixVente.toDouble(),novCodeBarre.toInt(),novType,novQuantite.toInt())
                              childRef?.setValue(carburantModifier)
                              notifyDataSetChanged()
                              alertDialog.dismiss()
                          }else{
                              Toast.makeText(context,"error",Toast.LENGTH_LONG).show()
                          }


                          }
                      true
                  }


                  R.id.supprimer ->{
                      ref.child(carburant.id!!).removeValue()
                      var idVente=refVente!!.push().key
                      val vente= Vent(carburant.id!!,carburant.designation,carburant.quantite,carburant.prixVente)
                      refVente.child(idVente!!).setValue(vente!!)
                      notifyDataSetChanged()
                      true
              }
                  else->true

              }

            }
            popupMenu.show()
            val popup=PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible=true
            val menu=popup.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)

        }


        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarburantViewHolder {
        val  view=LayoutInflater.from(context).inflate(R.layout.list_carburants,parent,false)
        return CarburantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCarburant.size
    }

    override fun onBindViewHolder(holder: CarburantViewHolder, position: Int) {
        val carburant=listCarburant[position]
        holder.codeBarre.text="Code barre : "+carburant.codeBarre.toString()
        holder.designation.text="Designation : "+carburant.designation.toString()
        holder.prixAchat.text="Prix achat : "+carburant.prixAchat.toString()+"DH"
    }

}




