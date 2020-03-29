package com.example.gsbandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import com.example.gsbandroid.metier.Activite
import com.example.gsbandroid.service.ServiceActivite
import org.jetbrains.anko.toast

class liste_activite : AppCompatActivity() {
    private lateinit var token: String
    private lateinit var idPraticien: String

    private var lesActivites = ArrayList<Activite>()
    private var lesNouvellesActivites = ArrayList<Activite>()

    private lateinit var lvActivite: ListView
    private lateinit var btnRetour: Button
    private lateinit var btnAjouter: Button
    private lateinit var spNouvellesActivites: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_activite)
        this.token = intent.getStringExtra("token")
        this.idPraticien = intent.getStringExtra("idPraticien")
        init()
        event()
        chargerActivite()
        chargerSpinner()
    }

    fun init() {
        this.lvActivite = findViewById(R.id.liste_activite_lv_lesactivites)
        this.btnRetour = findViewById(R.id.liste_activite_btn_retour)
        this.btnAjouter = findViewById(R.id.liste_activite_btn_ajouter)
        this.spNouvellesActivites = findViewById(R.id.liste_activite_sp_liste_nouvelle_activite)
    }

    fun event() {
        btnRetour.setOnClickListener {
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra("token", this.token)
            )
            finish()
        }
        btnAjouter.setOnClickListener {
            val activite = ServiceActivite()
            if (spNouvellesActivites.selectedItemPosition != 0) {
                val id_activite = lesNouvellesActivites[spNouvellesActivites.selectedItemPosition - 1].id_activite_compl.toString()
                val reponse = activite.inviter(this.idPraticien, this.token, id_activite)
                if (reponse.uneActivite != null) {
                    this.token = reponse.token
                    chargerActivite()
                    chargerSpinner()
                } else if (reponse.token == "Invalide"){
                    toast("Erreur : le token est invalide")
                } else {
                    toast("Erreur : " + reponse.Erreur)
                }
            }
        }
    }

    fun chargerActivite() {
        val activite = ServiceActivite()
        val reponseActivite = activite.getActivityofPraticien(idPraticien, token)
        if ((reponseActivite.Message == null) && (reponseActivite.token != "Invalide")) {
            this.lesActivites = reponseActivite.lesActivites!!
            this.token = reponseActivite.token
        } else if (reponseActivite.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponseActivite.Erreur)
        }
        val laListe = ArrayList<String>()
        for (uneActivite in lesActivites) {
            laListe.add(uneActivite.date_activite + ' ' + uneActivite.lieu_activite + ' ' + uneActivite.motif_activite + ' ' + uneActivite.theme_activite)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, laListe)
        lvActivite.adapter = adapter
    }

    fun chargerSpinner() {
        val activite = ServiceActivite()
        val reponseActivite = activite.getNewActivityofPraticien(idPraticien, token)
        if ((reponseActivite.Message == null) && (reponseActivite.token != "Invalide")) {
            this.lesNouvellesActivites = reponseActivite.lesActivites!!
            this.token = reponseActivite.token
        } else if (reponseActivite.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponseActivite.Erreur)
        }
        val laListe = ArrayList<String>()
        laListe.add("---")
        for (uneActivite in lesNouvellesActivites) {
            laListe.add(uneActivite.date_activite + ' ' + uneActivite.theme_activite)
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, laListe)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNouvellesActivites.adapter = adapter
    }
}
