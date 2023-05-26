package com.example.gestionpieceauto.DataClasses

import com.example.gestionpieceauto.DataClasses.Produit

class Carburant(id:String?=null,var designation:String?=null,prixAchat:Double?=null,dateAchat:String?=null,prixVente:Double?=null,codeBarre:Int?=null,var type:String?=null,quantite:Int?=null,fornisseur:String?=null):
    Produit(id,prixAchat,dateAchat,prixVente,codeBarre, quantite,fornisseur) {

    constructor(id: String,designation: String,prixAchat: Double,dateAchat:String,prixVente: Double,codeBarre: Int,type: String,quantite:Int,fornisseur:String) : this() {
        this.id=id
        this.designation=designation
        this.prixAchat=prixAchat
        this.prixVente=prixVente
        this.type=type
        this.dateAchat=dateAchat
        this.codeBarre=codeBarre
        this.quantite=quantite
        this.fornisseur=fornisseur
    }




    override fun afficher() {
        TODO("Not yet implemented")
    }
}