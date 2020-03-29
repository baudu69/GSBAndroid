package com.example.gsbandroid.metier

data class ReponsePraticien(
    val lesPraticiens: ArrayList<Praticien>,
    val token: String,
    val Message: String?,
    val Erreur: String?
)