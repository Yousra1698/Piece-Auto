package com.example.gestionpieceauto.Client

class Clients{
    var id:String?=null
    var nomComplete:String?=null
    var adresse:String?=null
    var Tel:String?=null
    constructor()
    constructor(id:String,nomComplete:String,adresse:String,Tel:String){
        this.id=id
        this.nomComplete=nomComplete
        this.adresse=adresse
        this.Tel= Tel
    }

}