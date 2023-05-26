package com.example.gestionpieceauto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionpieceauto.R
import com.example.gestionpieceauto.Vents.Vent

class AdapterVents(val context: Context, val listVente: ArrayList<Vent>): RecyclerView.Adapter<AdapterVents.VenteViewHolder> (){
    inner class VenteViewHolder(v: View): RecyclerView.ViewHolder(v){
        var produitVente=v.findViewById<TextView>(R.id.produitVente)
        var quantiteVente=v.findViewById<TextView>(R.id.quantiteVenteProduit)
        var prixVenteProduit=v.findViewById<TextView>(R.id.prixVenteProduit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenteViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.list_vents,parent,false)
        return VenteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVente.size
    }

    override fun onBindViewHolder(holder: VenteViewHolder, position: Int) {
        val vente=listVente[position]
        holder.produitVente.text="Produit : "+vente.produit.toString()
        holder.prixVenteProduit.text="Prix de vente : "+vente.prixVente.toString()+" DH "
        holder.quantiteVente.text="Quantité : "+vente.quantite.toString()

    }
}