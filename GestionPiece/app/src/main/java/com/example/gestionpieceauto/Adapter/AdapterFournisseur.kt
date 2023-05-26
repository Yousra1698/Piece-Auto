package com.example.gestionpieceauto.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Fournisseur.Fournisseurs
import com.example.gestionpieceauto.R
import com.google.firebase.database.FirebaseDatabase

class AdapterFournisseur(val context: Context, val ListFournisseur:ArrayList<Fournisseurs>):
    RecyclerView.Adapter<AdapterFournisseur.FournisseurViewHolder>() {

    inner class FournisseurViewHolder(var v: View): RecyclerView.ViewHolder(v){
        var NomComplete: TextView
        var NomSociete: TextView
        var Email: TextView
        var Telephone: TextView
        var mMenus: ImageView
        init {
            NomComplete=v.findViewById(R.id.text_nomCompletFournisseur)
            NomSociete=v.findViewById(R.id.text_NomSocieteFournisseur)
            Email=v.findViewById(R.id.text_EmailFournisseur)
            Telephone=v.findViewById(R.id.text_TelFournisseur)
            mMenus = v.findViewById(R.id.mMenus_Fournisseur)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        @SuppressLint("MissingInflatedId")
        private fun popupMenus(it: View) {
            val fournisseur=ListFournisseur[adapterPosition]
            val popupMenu= PopupMenu(context,it)
            val database= FirebaseDatabase.getInstance()
            var Ref=database.getReference("Fournisseurs")
            popupMenu.inflate(R.menu.show_menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.modifier->
                    {
                        var view =
                            LayoutInflater.from(context).inflate(R.layout.modifier_fournisseur, null)
                        var alertBuilder= androidx.appcompat.app.AlertDialog.Builder(context)
                        alertBuilder.setView(view)
                        val alertDialog=alertBuilder.create()
                        alertDialog.show()
                        view.findViewById<TextView>(R.id.edit_text_Nov_nomCompletFournisseur).setText(fournisseur.NomComplete.toString())
                        view.findViewById<TextView>(R.id.edit_text_Nov_nomSocieteFournisseur).setText(fournisseur.NomSociete.toString())
                        view.findViewById<TextView>(R.id.edit_text_Nov_EmailFournisseur).setText(fournisseur.Email.toString())
                        view.findViewById<TextView>(R.id.edit_text_Nov_TelFournisseur).setText(fournisseur.Tel.toString())

                        view.findViewById<Button>(R.id.btnModifierFournisseur).setOnClickListener {

                            var novnomComplete= view.findViewById<EditText>(R.id.edit_text_Nov_nomCompletFournisseur).text.toString()
                            var novnomSociete=view.findViewById<EditText>(R.id.edit_text_Nov_nomSocieteFournisseur).text.toString()
                            var novnEmail=view.findViewById<EditText>(R.id.edit_text_Nov_EmailFournisseur).text.toString()
                            var novTel=view.findViewById<EditText>(R.id.edit_text_Nov_TelFournisseur).text.toString()
                            var child =Ref?.child(fournisseur.id.toString())
                            if (novnomComplete.isNotEmpty()&&novnomSociete.isNotEmpty()&&novnEmail.isNotEmpty()&&novTel.isNotEmpty()){
                                var modifierFournisseurs= Fournisseurs(fournisseur.id!!,novnomComplete,novnomSociete,novnEmail,novTel.toInt())
                                child?.setValue(modifierFournisseurs)
                                notifyDataSetChanged()
                                alertDialog.dismiss()
                            }else{
                                Toast.makeText(context,"error",Toast.LENGTH_LONG).show()
                            }

                        }
                        true
                    }
                    R.id.supprimer ->{
                        Ref.child(fournisseur.id.toString()).removeValue()
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FournisseurViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v  = inflater.inflate(R.layout.list_item_fournisseur,parent,false)
        return FournisseurViewHolder(v)
    }

    override fun onBindViewHolder(holder: FournisseurViewHolder, position: Int) {
        val newList =ListFournisseur[position]
        holder.NomComplete.text =newList.NomComplete.toString()
        holder.NomSociete.text=newList.NomSociete.toString()
        holder.Email.text=newList.Email.toString()
        holder.Telephone.text=newList.Tel.toString()
    }

    override fun getItemCount(): Int {
        return ListFournisseur.size
    }
}
