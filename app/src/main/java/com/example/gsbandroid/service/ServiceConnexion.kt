package com.example.gsbandroid.service

import android.os.StrictMode
import com.example.gsbandroid.config.env
import com.example.gsbandroid.metier.Praticien
import com.example.gsbandroid.metier.ReponseConnexion
import com.example.gsbandroid.metier.ReponsePraticien
import com.google.gson.Gson
import java.net.URL

class ServiceConnexion {
    var url: String = ""
    init {
        var environnment = env()
        url = environnment.url
    }
    fun signIn(id: String, pwd: String): ReponseConnexion {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL(url + "signIn?id=" + id + "&mdp=" + pwd).readText()
        val gson = Gson()
        var Reponse: ReponseConnexion = gson.fromJson(json, ReponseConnexion::class.java)
        return Reponse
    }
}