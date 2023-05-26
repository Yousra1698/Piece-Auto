package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.DataClasses.Croix
import com.example.gestionpieceauto.R
import com.example.gestionpieceauto.Vents.Vent
import com.google.firebase.database.FirebaseDatabase

class AdapterCroix(val context: Context, val listCroix: ArrayList<Croix>): RecyclerView.Adapter<AdapterCroix.CroixViewHolder> (){
    inner class CroixViewHolder(v: View): RecyclerView.ViewHolder(v){

        var codeBarreCroix: TextView
        var typeCroix: TextView
        var prixAchatCroix: TextView
        var voitureCroix: TextView
        var menu: ImageView
        init {
            codeBarreCroix =v.findViewById(R.id.CodeBarreCroix)
            typeCroix=v.findViewById(R.id.typeCroix)
            voitureCroix=v.findViewById(R.id.VoitureCroix)
            prixAchatCroix=v.findViewById(R.id.prixAchatCroix)
            menu=v.findViewById(R.id.menu)
            menu.setOnClickListener { popupMenu(it) }
        }
        private fun popupMenu(v: View) {
            val popupMenu= PopupMenu(context,v)
            popupMenu.inflate(R.menu.show_menu)
            val croix=listCroix[adapterPosition]
            val database= FirebaseDatabase.getInstance()
            var ref=database.getReference("Croix")
            var refVente=database.getReference("Ventes")
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.modifier ->{
                        var view = LayoutInflater.from(context).inflate(R.layout.modifier_croix, null)
                        var alertBuilder= AlertDialog.Builder(context)
                        alertBuilder.setView(view)
                        val alertDialog=alertBuilder.create()
                        alertDialog.show()
                        view.findViewById<EditText>(R.id.nov_code_barre_croix).setText(croix.codeBarre.toString())
                        view.findViewById<EditText>(R.id.nov_type_croix).setText(croix.typeCroix)
                        view.findViewById<EditText>(R.id.nov_prix_achat_croix).setText(croix.prixAchat.toString())
                        view.findViewById<EditText>(R.id.nov_date_achat_croix).setText(croix.dateAchat.toString())
                        view.findViewById<EditText>(R.id.nov_prix_vente_croix).setText(croix.prixVente.toString())
                        view.findViewById<EditText>(R.id.nov_fornisseur_croix).setText(croix.fornisseur.toString())
                        view.findViewById<EditText>(R.id.nov_quantite_croix).setText(croix.quantite.toString())
                        view.findViewById<EditText>(R.id.nov_voiture_croix).setText(croix.voitureCroix.toString())
                        view.findViewById<Button>(R.id.button_modifier_croix).setOnClickListener {
                            var novCodeBarreCroix= view.findViewById<EditText>(R.id.nov_code_barre_croix).text.toString()
                            var novtypeCroix = view.findViewById<EditText>(R.id.nov_type_croix).text.toString()
                            var novPrixAchatCroix = view.findViewById<EditText>(R.id.nov_prix_achat_croix).text.toString()
                            var novDateAchatCroix = view.findViewById<EditText>(R.id.nov_date_achat_croix).text.toString()
                            var novPrixVenteCroix = view.findViewById<EditText>(R.id.nov_prix_vente_croix).text.toString()
                            var novFornisseurCroix = view.findViewById<EditText>(R.id.nov_fornisseur_croix).text.toString()
                            var novQuantiteCroix = view.findViewById<EditText>(R.id.nov_quantite_croix).text.toString()
                            var novVoitureCroix = view.findViewById<EditText>(R.id.nov_voiture_croix).text.toString()
                            var childRef = ref?.child(croix.id.toString())
                            if (novCodeBarreCroix.isEmpty()&&novtypeCroix.isNotEmpty()&&novPrixAchatCroix.isNotEmpty()&&novDateAchatCroix.isNotEmpty()&&novPrixVenteCroix.isNotEmpty()&&novFornisseurCroix.isNotEmpty()&&novQuantiteCroix.isNotEmpty()&&novVoitureCroix.isNotEmpty()){
                                var croixModifier = Croix(croix.id!!,novtypeCroix,novPrixAchatCroix.toDouble(),novPrixVenteCroix.toDouble(),novDateAchatCroix,novQuantiteCroix.toInt(),novCodeBarreCroix.toInt(),novFornisseurCroix,novVoitureCroix)
                                childRef?.setValue(croixModifier)
                                notifyDataSetChanged()
                                alertDialog.dismiss()
                            }else{
                                Toast.makeText(context,"error",Toast.LENGTH_LONG).show()
                            }


                        }
                        true
                    }


                    R.id.supprimer ->{
                        ref.child(croix.id!!).removeValue()
                        var idVente=refVente!!.push().key
                        val vente=Vent(croix.id!!,croix.typeCroix,croix.quantite,croix.prixVente)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CroixViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.list_croix,parent,false)
        return CroixViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCroix.size
    }

    override fun onBindViewHolder(holder: AdapterCroix.CroixViewHolder, position: Int) {
        val croix=listCroix[position]
        holder.codeBarreCroix.text="Code barre : "+ croix.codeBarre.toString()
        holder.typeCroix.text="Type : "+ croix.typeCroix.toString()
        holder.voitureCroix.text="Voiture : "+croix.voitureCroix.toString()
        holder.prixAchatCroix.text="Prix achat : "+ croix.prixAchat.toString()+" DH "
    }


}