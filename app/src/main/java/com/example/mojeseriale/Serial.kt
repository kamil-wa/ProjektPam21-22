package com.example.mojeseriale

class Serial {
    var name: String
    var ocena: Int
    var ocenaFilmWeb: Int

constructor(name: String, ocena: Int, ocenaFilmWeb: Int ){
    this.name = name
    this.ocena = ocena
    this.ocenaFilmWeb = ocenaFilmWeb
}

    fun setGrade (newGrade: Int){
        ocena = newGrade

    }
}