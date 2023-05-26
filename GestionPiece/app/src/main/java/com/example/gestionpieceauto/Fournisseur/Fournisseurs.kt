package com.example.gestionpieceauto.Fournisseur

class Fournisseurs{
    var id:String?=null
    var NomComplete:String?=null
    var NomSociete:String?=null
    var Email:String?=null
    var Tel:Int?=null
    constructor(){
    }
    constructor(id: String, NomComplete:String, NomSociete:String, Email:String, Tel: Int){
        this.id=id
        this.NomComplete=NomComplete
        this.NomSociete=NomSociete
        this.Email=Email
        this.Tel=Tel
    }
}
