package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.DataClasses.Croix
import com.example.gestionpieceauto.R

class AdapterStockCroix(val context: Context, val listCroix: ArrayList<Croix>): RecyclerView.Adapter<AdapterStockCroix.CroixVH> (){
    inner class CroixVH(val v: View): RecyclerView.ViewHolder(v){
        var marqueCroix =v.findViewById<TextView>(R.id.marqueCroix)
        var prixAchatCroix=v.findViewById<TextView>(R.id.prixAchatCroix)
        var prixVenteCroix=v.findViewById<TextView>(R.id.prixVenteCroix)
        var dateAchatCroix=v.findViewById<TextView>(R.id.dateAchatCroix)
        var fornisseurCroix=v.findViewById<TextView>(R.id.fornisseurCroix)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterStockCroix.CroixVH {
        val  view= LayoutInflater.from(context).inflate(R.layout.activity_stock_croix,parent,false)
        return CroixVH(view)    }

    override fun onBindViewHolder(holder: CroixVH, position: Int) {
        val croix=listCroix[position]
        holder.marqueCroix.text="Marque : "+croix.typeCroix.toString()
        holder.prixAchatCroix.text="Prix achat : "+croix.prixAchat.toString()+" DH "
        holder.dateAchatCroix.text="Date achat : "+croix.dateAchat.toString()
        holder.prixVenteCroix.text="Prix de vente : "+croix.prixVente.toString()+" DH "
        holder.fornisseurCroix.text="Fornisseur : "+croix.fornisseur.toString()
    }
    override fun getItemCount(): Int {
        return listCroix.size
    }



}