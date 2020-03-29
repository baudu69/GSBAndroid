package com.example.gsbandroid

import android.annotation.SuppressLint
import android.content.ClipData.newIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    private lateinit var btnListerPraticien: Button
    private lateinit var btnQuitter: Button
    private lateinit var btnConnexion: Button
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        event()
    }

    private fun init() {
        this.btnListerPraticien = findViewById(R.id.main_btn_lister_praticien)
        this.btnQuitter = findViewById(R.id.main_btn_lister_quitter)
        this.btnConnexion = findViewById(R.id.main_btn_lister_connexion)
        this.btnListerPraticien.isEnabled = false
        this.btnListerPraticien.isClickable = false
        this.token = null
        this.btnConnexion.text = "Connexion"
    }

    private fun event() {
        btnListerPraticien.setOnClickListener {
            val intent = Intent(this, liste_praticien::class.java)
            intent.putExtra("token", this.token)
            startActivityForResult(intent, 0)
        }
        btnQuitter.setOnClickListener {
            finish()
        }

        btnConnexion.setOnClickListener {
            if (this.token == null) {
                val intent = Intent(this, connexion::class.java)
                startActivityForResult(intent, 0)
            } else {
                toast("Vous etes bien deconnecte")
                init()
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            this.token =  data.getStringExtra("token")
            this.btnConnexion.text = "Deconnexion"
            this.btnListerPraticien.isEnabled = true
            this.btnListerPraticien.isClickable = true
        }
    }
}
