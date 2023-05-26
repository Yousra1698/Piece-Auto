package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.Achats.Achat
import com.example.gestionpieceauto.R


class AdapterAchat(val context: Context, val listAchat: ArrayList<Achat>): RecyclerView.Adapter<AdapterAchat.AchatViewHolder> (){
    inner class AchatViewHolder(v: android.view.View):RecyclerView.ViewHolder(v){
        var produit=v.findViewById<TextView>(R.id.produitAchat)
        var quantite=v.findViewById<TextView>(R.id.quantiteAchatProduit)
        var prix=v.findViewById<TextView>(R.id.prixAchatProduit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_achats,parent,false)
        return AchatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAchat.size
    }

    override fun onBindViewHolder(holder: AchatViewHolder, position: Int) {
        val achat=listAchat[position]
        holder.produit.text="Produit : "+achat.produit.toString()
        holder.prix.text="Prix d'achat : "+achat.prixAchat.toString()+" DH "
        holder.quantite.text="Quantité : "+achat.quantite.toString()

    }
}