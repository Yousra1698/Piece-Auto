package com.example.gestionpieceauto.DataClasses

abstract class Produit(var id: String?, var prixAchat: Double?,var dateAchat:String?,var prixVente: Double?,var codeBarre:Int?,var quantite:Int?,var fornisseur:String? ) {
    abstract fun afficher()
}