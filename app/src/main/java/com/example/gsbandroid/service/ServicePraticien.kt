package com.example.gsbandroid.service

import android.os.StrictMode
import android.widget.TextView
import com.example.gsbandroid.R
import com.example.gsbandroid.config.env
import com.example.gsbandroid.metier.Praticien
import com.example.gsbandroid.metier.ReponsePraticien
import com.example.gsbandroid.metier.ReponseTypes
import com.example.gsbandroid.metier.Type
import java.net.URL
import com.google.gson.Gson

class ServicePraticien {
    var url: String = ""
    init {
        var environnment = env()
        url = environnment.url
    }
    fun getLesPraticiens(token: String?): ReponsePraticien {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/listePraticien?token=" + token).readText()
        val gson = Gson()
        var Reponse: ReponsePraticien = gson.fromJson(json, ReponsePraticien::class.java)
        return Reponse
    }
    fun getLesPraticiensType(token: String?, idType: String): ReponsePraticien {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/listePraticienNomType?token=" + token + "&type=" + idType).readText()
        val gson = Gson()
        var Reponse: ReponsePraticien = gson.fromJson(json, ReponsePraticien::class.java)
        return Reponse
    }
    fun getLesPraticiensNom(token: String?, nomPraticien: String): ReponsePraticien {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/listePraticienNomType?token=" + token + "&nomPraticien=" + nomPraticien).readText()
        val gson = Gson()
        var Reponse: ReponsePraticien = gson.fromJson(json, ReponsePraticien::class.java)
        return Reponse
    }
    fun getLesPraticiensNomType(token: String?, idType: String, nomPraticien: String): ReponsePraticien {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/listePraticienNomType?token=" + token + "&type=" + idType + "&nomPraticien=" + nomPraticien).readText()
        val gson = Gson()
        var Reponse: ReponsePraticien = gson.fromJson(json, ReponsePraticien::class.java)
        return Reponse
    }

    fun getLesTypes(token: String?): ReponseTypes {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "praticien/listeTypes?token=" + token).readText()
        val gson = Gson()
        var Reponse: ReponseTypes = gson.fromJson(json, ReponseTypes::class.java)
        return Reponse
    }
}