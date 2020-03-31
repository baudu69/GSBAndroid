package com.example.gsbandroid.service

import android.os.StrictMode
import com.example.gsbandroid.config.env
import com.example.gsbandroid.metier.ReponseListeActivite
import com.example.gsbandroid.metier.ReponsePraticien
import com.google.gson.Gson
import java.lang.Exception
import java.net.URL

class ServiceActivite {
    var url: String = ""
    init {
        var environnment = env()
        url = environnment.url
    }
    fun getActivityofPraticien(idPraticien: String, token: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url +"praticien/listeActivite?idPraticien=" + idPraticien + "&token=" + token).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
    fun getNewActivityofPraticien(idPraticien: String, token: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url +"praticien/listeActivitesNonPraticien?idPraticien=" + idPraticien + "&token=" + token).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
    fun inviter(idPraticien: String, token: String, idActivite:String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/ajouterActivitePraticien?idPraticien=" + idPraticien + "&token=" + token + "&idActivite=" + idActivite).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
    fun details(idPraticien: String, token: String, idActivite: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/listerUneSpecialite?idPraticien=" + idPraticien + "&token=" + token + "&idActivite=" + idActivite).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }

    fun specialiser(idPraticien: String, token: String, idActivite: String, faire: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/specialiser?idPraticien=" + idPraticien + "&token=" + token + "&idActivite=" + idActivite + "&faire=" + faire).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }

    fun supprimerInvitation(idPraticien: String, token: String, idActivite: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/supprimerActivite?idPraticien=" + idPraticien + "&token=" + token + "&idActivite=" + idActivite).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
}