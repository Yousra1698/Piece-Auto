package com.example.gestionpieceauto.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.DataClasses.Huile
import com.example.gestionpieceauto.R
import com.example.gestionpieceauto.Vents.Vent
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context

class AdapterHuile(val context: android.content.Context, val listHuile: ArrayList<Huile>): RecyclerView.Adapter<AdapterHuile.HuileViewHolder> (){
    inner class HuileViewHolder( v: View): RecyclerView.ViewHolder(v){

        var codeBarreHuile: TextView
        var marqueHil:TextView
        var typeHuile: TextView
        var prixAchatHuile: TextView
        var menu: ImageView
        init {
            codeBarreHuile =v.findViewById(R.id.CodeBarreHuile)
            marqueHil=v.findViewById(R.id.Marque)
            typeHuile=v.findViewById(R.id.typeHuile)
            prixAchatHuile=v.findViewById(R.id.prixAchatHuile)
            menu=v.findViewById(R.id.menu)
            menu.setOnClickListener { popupMenu(it) }
        }
        @SuppressLint("MissingInflatedId")
        private fun popupMenu(v: View) {
            val popupMenu= PopupMenu(context,v)
            popupMenu.inflate(R.menu.show_menu)
            val huile=listHuile[adapterPosition]
            val database= FirebaseDatabase.getInstance()
            var ref=database.getReference("Huiles")
            var refVente=database.getReference("Ventes")
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.modifier ->{
                        var view = LayoutInflater.from(context).inflate(R.layout.modifier_huile, null)
                        var alertBuilder= AlertDialog.Builder(context)
                        alertBuilder.setView(view)
                        val alertDialog=alertBuilder.create()
                        alertDialog.show()
                        view.findViewById<Button>(R.id.button_modifier_huile).setOnClickListener {
                            var novCodeBarreHuile= view.findViewById<EditText>(R.id.nov_code_barre_huile).text.toString()
                            var novtypeHuile = view.findViewById<EditText>(R.id.nov_type_huile).text.toString()
                            var novPrixAchatHuile = view.findViewById<EditText>(R.id.nov_prix_achat_huile).text.toString()
                            var novDateAchatHuile = view.findViewById<EditText>(R.id.nov_date_achat_huile).text.toString()
                            var novPrixVenteHuile = view.findViewById<EditText>(R.id.nov_prix_vente_huile).text.toString()
                            var novFornisseurHuile = view.findViewById<EditText>(R.id.nov_fornisseur_huile).text.toString()
                            var novQuantiteHuile = view.findViewById<EditText>(R.id.nov_quantite_huile).text.toString()
                            var novMarqueHuile = view.findViewById<EditText>(R.id.nov_marque_huile).text.toString()
                            var childRef = ref?.child(huile.id.toString())
                            var huileModifier = Huile(huile.id!!, novMarqueHuile,novtypeHuile,novCodeBarreHuile.toInt(),novPrixAchatHuile.toDouble(),novDateAchatHuile,novPrixVenteHuile.toDouble(),novQuantiteHuile.toInt(),novFornisseurHuile)
                            childRef?.setValue(huileModifier)
                            notifyDataSetChanged()
                            alertDialog.dismiss()

                        }
                        true
                    }


                    R.id.supprimer ->{
                        ref.child(huile.id!!).removeValue()
                        var idVente=refVente!!.push().key
                        val vente= Vent(huile.id!!,huile.marqueOil,huile.quantite,huile.prixVente)
                        refVente.child(idVente!!).setValue(vente!!)
                        notifyDataSetChanged()
                        true
                    }
                    else->true

                }

            }
            popupMenu.show()
            val popup= PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible=true
            val menu=popup.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HuileViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.list_huile,parent,false)
        return HuileViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterHuile.HuileViewHolder, position: Int) {
        val huile=listHuile[position]
        holder.codeBarreHuile.text="Code barre : "+ huile.codeBarre.toString()
        holder.marqueHil.text="Marque : "+huile.marqueOil.toString()
        holder.typeHuile.text="Type : "+ huile.typeOil.toString()
        holder.prixAchatHuile.text="Prix achat : "+ huile.prixAchat.toString()+" DH "
    }

    override fun getItemCount(): Int {
        return listHuile.size
    }
}