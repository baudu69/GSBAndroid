package com.example.gsbandroid.metier

data class Activite(
    val date_activite: String,
    val id_activite_compl: Int,
    val id_praticien: Int,
    val lieu_activite: String,
    val motif_activite: String,
    val specialiste: String,
    val theme_activite: String
)