package com.example.gsbandroid.metier

data class ReponseTypes(
    val lesTypes: ArrayList<Type>,
    val token: String,
    val Message: String?,
    val Erreur: String?
)