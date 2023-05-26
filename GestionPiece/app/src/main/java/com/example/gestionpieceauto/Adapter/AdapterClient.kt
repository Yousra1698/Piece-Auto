package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Client.Clients
import com.example.gestionpieceauto.R
import com.google.firebase.database.FirebaseDatabase

class AdapterClient(val context: Context, val ListClient:ArrayList<Clients>): RecyclerView.Adapter<AdapterClient.ClientViewHolder>() {
    inner class ClientViewHolder(val v: View) : RecyclerView.ViewHolder(v){
        var nomComplete: TextView
        var adresse: TextView
        var Tel: TextView
        var mMenu: ImageView
        init {
            nomComplete=v.findViewById<TextView>(R.id.nomComplet_Client)
            adresse=v.findViewById<TextView>(R.id.adresse_Client)
            Tel=v.findViewById<TextView>(R.id.Tel_Client)
            mMenu=v.findViewById<ImageView>(R.id.mMenus_Client)
            mMenu.setOnClickListener {
                popupMenu(it)
            }
        }

        private fun popupMenu(it: View) {
            val client=ListClient[adapterPosition]
            val popupMenu= PopupMenu(context,it)
            val database= FirebaseDatabase.getInstance()
            var Ref=database.getReference("Clients")
            popupMenu.inflate(R.menu.show_menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.modifier->
                    {
                        var view =
                            LayoutInflater.from(context).inflate(R.layout.modifier_client, null)
                        var alertBuilder= AlertDialog.Builder(context)
                        alertBuilder.setView(view)
                        val alertDialog=alertBuilder.create()
                        alertDialog.show()
                        view.findViewById<TextView>(R.id.edit_text_Nov_nomCompletClient).setText(client.nomComplete.toString())
                        view.findViewById<TextView>(R.id.edit_text_Nov_adresseClient).setText(client.adresse.toString())
                        view.findViewById<TextView>(R.id.edit_text_Nov_TelClient).setText(client.Tel.toString())
                        view.findViewById<Button>(R.id.btnModifierClient).setOnClickListener {
                            var novnomComplete= view.findViewById<EditText>(R.id.edit_text_Nov_nomCompletClient).text.toString()
                            var novAdress=view.findViewById<EditText>(R.id.edit_text_Nov_adresseClient).text.toString()
                            var novTel=view.findViewById<EditText>(R.id.edit_text_Nov_TelClient).text.toString()
                            var child =Ref?.child(client.id.toString())
                            var modifierClients= Clients(client.id!!,novnomComplete,novAdress,novTel)
                            child?.setValue(modifierClients)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
                        true
                    }
                    R.id.supprimer->{
                        Ref.child(client.id.toString()).removeValue()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.list_item_client,parent,false)
        return ClientViewHolder(view)
    }
    override fun getItemCount(): Int {
        return ListClient.size
    }
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client=ListClient[position]
        holder.nomComplete.text=client.nomComplete
        holder.adresse.text=client.adresse
        holder.Tel.text=client.Tel.toString()
    }
}
