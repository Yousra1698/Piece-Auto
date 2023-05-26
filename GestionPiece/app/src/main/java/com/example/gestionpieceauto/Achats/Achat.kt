package com.example.gestionpieceauto.Achats

class Achat(var id:String?=null,var produit:String?=null,var quantite:Int?=null,
            var prixAchat:Double?=null) {
    constructor(id: String,produit: String,quantite: Int,prixAchat: Double):this(){
        this.id=id
        this.produit=produit
        this.prixAchat=prixAchat
        this.quantite=quantite
    }

}