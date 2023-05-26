package com.example.gestionpieceauto.DataClasses

class Huile( id:String?=null,var marqueOil:String?=null,var typeOil:String?=null,codeBarre:Int?=null,prixAchat:Double?=null,dateAchat:String?=null,prixVente:Double?=null,quantite:Int?=null,fornisseur:String?=null):Produit(id,prixAchat,dateAchat, prixVente,codeBarre, quantite, fornisseur){
    override fun afficher() {
        TODO("Not yet implemented")
    }
}