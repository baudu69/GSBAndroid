package com.example.gsbandroid.service

import android.os.StrictMode
import com.example.gsbandroid.metier.ReponseListeActivite
import com.example.gsbandroid.metier.ReponsePraticien
import com.google.gson.Gson
import java.net.URL

class ServiceActivite {
    fun getActivityofPraticien(idPraticien: String, token: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL("http://192.168.1.31/api/json/praticien/listeActivite?idPraticien=" + idPraticien + "&token=" + token).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
    fun getNewActivityofPraticien(idPraticien: String, token: String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL("http://192.168.1.31/api/json/praticien/listeActivitesNonPraticien?idPraticien=" + idPraticien + "&token=" + token).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
    fun inviter(idPraticien: String, token: String, idActivite:String): ReponseListeActivite {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL("http://192.168.1.31/api/json/praticien/ajouterActivitePraticien?idPraticien=" + idPraticien + "&token=" + token + "&idActivite=" + idActivite).readText()
        val gson = Gson()
        var Reponse: ReponseListeActivite = gson.fromJson(json, ReponseListeActivite::class.java)
        return Reponse
    }
}