package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.DataClasses.Huile
import com.example.gestionpieceauto.R

class AdapterStockHuile(val context: Context, val listHuile: ArrayList<Huile>): RecyclerView.Adapter<AdapterStockHuile.HuileVH> (){
    inner class HuileVH(val v: View): RecyclerView.ViewHolder(v){
        var marqueHuile =v.findViewById<TextView>(R.id.marqueHuile)
        var prixAchatHuile=v.findViewById<TextView>(R.id.prixAchatHuile)
        var prixVenteHuile=v.findViewById<TextView>(R.id.prixVenteHuile)
        var dateAchatHuile=v.findViewById<TextView>(R.id.dateAchatHuile)
        var fornisseurHuile=v.findViewById<TextView>(R.id.fornisseurHuile)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HuileVH {
        val  view= LayoutInflater.from(context).inflate(R.layout.activity_stock_huile,parent,false)
        return HuileVH(view)    }

    override fun onBindViewHolder(holder: HuileVH, position: Int) {
        val huile=listHuile[position]
        holder.marqueHuile.text="Marque : "+huile.marqueOil.toString()
        holder.prixAchatHuile.text="Prix achat : "+huile.prixAchat.toString()+" DH "
        holder.dateAchatHuile.text="Date achat : "+huile.dateAchat.toString()
        holder.prixVenteHuile.text="Prix de vente : "+huile.prixVente.toString()+" DH "
        holder.fornisseurHuile.text="Fornisseur : "+huile.fornisseur.toString()
    }

    override fun getItemCount(): Int {
        return listHuile.size
    }



}