package com.example.gsbandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.gsbandroid.service.ServiceConnexion
import org.jetbrains.anko.toast
import kotlin.math.sign


class connexion : AppCompatActivity() {

    private lateinit var ptId: EditText
    private lateinit var ptPwd: EditText
    private lateinit var btnQuitter: Button
    private lateinit var btnValider: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)
        init()
        event()
        //autoConnect()
    }

    fun init() {
        this.ptId = findViewById(R.id.connexion_pt_id)
        this.ptPwd = findViewById(R.id.connexion_pt_pwd)
        this.btnQuitter = findViewById(R.id.connexion_btn_annuler)
        this.btnValider = findViewById(R.id.connexion_btn_valider)
    }

    fun event() {
        btnQuitter.setOnClickListener {
            finish()
        }
        btnValider.setOnClickListener {
            signIn()
        }
    }

    fun signIn() {
        val laConnexion = ServiceConnexion()
        var etat = laConnexion.signIn(this.ptId.text.toString(), this.ptPwd.text.toString())
        if (etat.message == "ok") {
            toast("Vous etes bien connecte")
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra("token", etat.token)
            )
            finish()
        } else {
            toast(etat.message)
        }
    }

    fun autoConnect() {
        this.ptId.setText("Agsbsio")
        this.ptPwd.setText("secret")
        signIn()
    }

}
