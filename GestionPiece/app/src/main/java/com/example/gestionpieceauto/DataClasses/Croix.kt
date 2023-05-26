package com.example.gestionpieceauto.DataClasses

class Croix(id:String?=null,var typeCroix:String?=null,prixAchat:Double?=null,prixVente:Double?=null,dateAchat:String?=null,codeBarre:Int?=null,quantite:Int?=null,fornisseur:String?=null,var voitureCroix:String?=null):Produit(id,prixAchat,dateAchat,prixVente,codeBarre,quantite,fornisseur) {
    constructor(id: String,typeCroix: String,prixAchat: Double,prixVente: Double,dateAchat: String,codeBarre: Int,quantite: Int,fornisseur: String,voitureCroix: String):this(){
        this.id=id
        this.typeCroix=typeCroix
        this.prixAchat=prixAchat
        this.prixVente=prixVente
        this.dateAchat=dateAchat
        this.voitureCroix=voitureCroix
        this.quantite=quantite
        this.fornisseur=fornisseur
        this.codeBarre=codeBarre
    }
    override fun afficher() {
        TODO("Not yet implemented")
    }
}