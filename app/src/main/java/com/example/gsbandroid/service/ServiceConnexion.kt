package com.example.gsbandroid.service

import android.os.StrictMode
import com.example.gsbandroid.metier.Praticien
import com.example.gsbandroid.metier.ReponseConnexion
import com.example.gsbandroid.metier.ReponsePraticien
import com.google.gson.Gson
import java.net.URL

class ServiceConnexion {

    fun signIn(id: String, pwd: String): ReponseConnexion {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var json = URL("http://192.168.1.31/api/json/signIn?id=" + id + "&mdp=" + pwd).readText()
        val gson = Gson()
        var Reponse: ReponseConnexion = gson.fromJson(json, ReponseConnexion::class.java)
        return Reponse
    }
}