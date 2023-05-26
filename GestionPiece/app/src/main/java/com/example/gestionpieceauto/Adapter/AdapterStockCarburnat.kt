package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.DataClasses.Carburant
import com.example.gestionpieceauto.R

class AdapterStockCarburnat (val context: Context, val listCarburant: ArrayList<Carburant>):RecyclerView.Adapter<AdapterStockCarburnat.CarburantVH> (){
    inner class CarburantVH(val v:View):RecyclerView.ViewHolder(v){
        var designation=v.findViewById<TextView>(R.id.designation)
        var prixAchat=v.findViewById<TextView>(R.id.prixAchat)
        var prixVente=v.findViewById<TextView>(R.id.prixVente)
        var dateAchat=v.findViewById<TextView>(R.id.dateAchat)
        var fornisseur=v.findViewById<TextView>(R.id.fornisseur)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterStockCarburnat.CarburantVH {
        val  view=LayoutInflater.from(context).inflate(R.layout.activity_stock_carburants,parent,false)
        return CarburantVH(view)    }

    override fun onBindViewHolder(holder: CarburantVH, position: Int) {
        val carburant=listCarburant[position]
        holder.designation.text="Designation : "+carburant.designation.toString()
        holder.prixAchat.text="Prix achat : "+carburant.prixAchat.toString()+" DH "
        holder.dateAchat.text="Date achat : "+carburant.dateAchat.toString()
        holder.prixVente.text="Prix de vente : "+carburant.prixVente.toString()+" DH "
        holder.fornisseur.text="Fornisseur : "+carburant.fornisseur.toString()
    }

    override fun getItemCount(): Int {
        return listCarburant.size
    }



}