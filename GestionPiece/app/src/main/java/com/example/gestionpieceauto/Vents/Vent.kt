package com.example.gestionpieceauto.Vents

class Vent(var idVente: String?=null,var produit:String?=null,var quantite:Int?=null,var prixVente:Double?=null) {
    constructor(idVente: String,produit: String,quantite: Int,prixVente: Double):this(){
        this.idVente=idVente
        this.produit=produit
        this.quantite=quantite
        this.prixVente=prixVente
    }
}