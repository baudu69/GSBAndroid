package com.example.gsbandroid

import android.app.Activity
import android.app.Service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.gsbandroid.metier.Activite
import com.example.gsbandroid.service.ServiceActivite
import org.jetbrains.anko.toast
import org.w3c.dom.Text

class detail_activite : AppCompatActivity() {

    private lateinit var token: String
    private lateinit var idPraticien: String
    private lateinit var idActivite: String

    private lateinit var uneActivite: Activite

    private lateinit var btnSpecialiste: Button
    private lateinit var btnRetour: Button
    private lateinit var btnSupprimer: Button
    private lateinit var tvTitre: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvLieu: TextView
    private lateinit var tvMotif: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activite)
        this.token = intent.getStringExtra("token")
        this.idPraticien = intent.getStringExtra("idPraticien")
        this.idActivite = intent.getStringExtra("idActivite")
        init()
        event()
    }

    fun init() {
        this.btnSpecialiste = findViewById(R.id.activite_detail_btn_specialiste)
        this.btnRetour = findViewById(R.id.activite_detail_btn_retour)
        this.btnSupprimer = findViewById(R.id.activite_detail_btn_supprimer)
        this.tvTitre = findViewById(R.id.activite_detail_tv_titre)
        this.tvDate = findViewById(R.id.activite_detail_tv_date)
        this.tvLieu = findViewById(R.id.activite_detail_tv_lieu)
        this.tvMotif = findViewById(R.id.activite_detail_tv_motif)
        chargerActivite()
    }

    fun chargerActivite() {
        val activite = ServiceActivite()
        val reponseActivite = activite.details(this.idPraticien, this.token, this.idActivite)
        if ((reponseActivite.Message == "OK") && (reponseActivite.token != "Invalide")) {
            this.uneActivite = reponseActivite.uneActivite!!
            this.token = reponseActivite.token!!
            remplirPage()
        } else if (reponseActivite.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponseActivite.Erreur)
        }
    }

    fun remplirPage() {
        this.tvTitre.text = "Detail de l'activite : " + uneActivite.theme_activite
        this.tvMotif.text = "Motif de l'activite : " + uneActivite.motif_activite
        this.tvDate.text = "Date de l'activite : " + uneActivite.date_activite
        this.tvLieu.text = "Lieu de l'activite :" + uneActivite.lieu_activite
        if (uneActivite.specialiste == "O") {
            this.btnSpecialiste.text = "Oui"
        } else {
            this.btnSpecialiste.text = "Non"
        }
    }

    fun specialiser() {
        val faire: String
        if (btnSpecialiste.text == "Oui") {
            faire = "N"
        } else {
            faire = "0"
        }
        val activite = ServiceActivite()
        val reponse = activite.specialiser(idPraticien, token, idActivite, faire)
        if ((reponse.Message == "OK") && (reponse.token != "Invalide")) {
            this.token = reponse.token.toString()
            chargerActivite()
        } else if (reponse.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponse.Erreur)
        }
    }

    fun supprimer() {
        val activite = ServiceActivite()
        val reponse = activite.supprimerInvitation(idPraticien, token, idActivite)
        if ((reponse.Message == "ok") && (reponse.token != "Invalide")) {
            this.token = reponse.token.toString()
            toast("L'invitation " + uneActivite.theme_activite + " a ete supprime")
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra("token", this.token)
            )
            finish()
        } else if (reponse.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponse.Erreur)
        }
    }

    fun event() {
        btnRetour.setOnClickListener {
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra("token", this.token)
            )
            finish()
        }
        btnSpecialiste.setOnClickListener {
            specialiser()
        }
        btnSupprimer.setOnClickListener {
            supprimer()
        }
    }
}
