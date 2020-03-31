package com.example.gsbandroid.metier

data class ReponseListeActivite(
    val lesActivites: ArrayList<Activite>?,
    val uneActivite: Activite?,
    val token: String?,
    val Message: String?,
    val Erreur: String?
)