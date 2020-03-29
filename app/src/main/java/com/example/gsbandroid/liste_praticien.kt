package com.example.gsbandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gsbandroid.metier.Praticien
import com.example.gsbandroid.metier.Type
import com.example.gsbandroid.service.ServicePraticien
import org.jetbrains.anko.toast


class liste_praticien : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var lesPraticien: ArrayList<Praticien> = ArrayList<Praticien>()
    private var lesTypes: ArrayList<Type> = ArrayList<Type>()
    private lateinit var lvLaListeDesPraticiens: ListView
    private lateinit var spListeTypes: Spinner
    private lateinit var btnRetour: Button
    private lateinit var etNomPratien: EditText
    private var token: String? = null
    private var positionType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_praticien)
        this.token = intent.getStringExtra("token")
        this.init()
        this.chargerTableau()
        this.chargerTypes()
        this.event()
    }

    private fun init() {
        val praticien = ServicePraticien()
        val reponsePraticien = praticien.getLesPraticiens(token)
        if ((reponsePraticien.Message == null) && (reponsePraticien.token != "Invalide")) {
            this.lesPraticien = reponsePraticien.lesPraticiens
            this.token = reponsePraticien.token
        } else if (reponsePraticien.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponsePraticien.Erreur)
        }
        val reponseType = praticien.getLesTypes(token)
        if ((reponseType.Message == null) && (reponseType.token != "Invalide")) {
            this.lesTypes = reponseType.lesTypes
            this.token = reponseType.token
        } else if (reponseType.token == "Invalide"){
            toast("Erreur : le token est invalide")
        } else {
            toast("Erreur : " + reponseType.Erreur)
        }
        this.lvLaListeDesPraticiens = findViewById(R.id.liste_praticien_lv_lespraticiens)
        this.spListeTypes = findViewById(R.id.liste_praticien_sp_lestypes)
        this.btnRetour = findViewById(R.id.liste_praticien_btn_retour)
        this.etNomPratien = findViewById(R.id.liste_praticien_et_nom)
    }

    private fun chargerTableau() {
        val laListe = ArrayList<String>()
        for (unPraticien in lesPraticien) {
            laListe.add(unPraticien.nom_praticien + ' ' + unPraticien.prenom_praticien)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, laListe)
        lvLaListeDesPraticiens.adapter = adapter
    }

    private fun chargerTypes() {
        val laListe = ArrayList<String>()
        laListe.add("---")
        for (unType in lesTypes) {
            laListe.add(unType.lib_type_praticien)
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, laListe)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spListeTypes.adapter = adapter
    }

    private fun event() {
        btnRetour.setOnClickListener {
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra("token", this.token)
            )
            finish()
        }
        spListeTypes.onItemSelectedListener = this
        lvLaListeDesPraticiens.setOnItemClickListener { parent, view, position, id ->
            toast(position.toString())
        }
        etNomPratien.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val praticien = ServicePraticien()
                val reponsePraticien = praticien.getLesPraticiensNomType(token, positionType.toString(), etNomPratien.text.toString())
                if ((reponsePraticien.Message == null) && (reponsePraticien.token != "Invalide")) {
                    lesPraticien = reponsePraticien.lesPraticiens
                    token = reponsePraticien.token
                } else if (reponsePraticien.token == "Invalide"){
                    toast("Erreur : le token est invalide")
                } else {
                    toast("Erreur : " + reponsePraticien.Erreur)
                }
                chargerTableau()
            }
        })
        lvLaListeDesPraticiens.setOnItemClickListener { parent, view, position, id ->
            val praticienSelectionne = lesPraticien.get(position)
            val intent = Intent(this, liste_activite::class.java)
            intent.putExtra("idPraticien", praticienSelectionne.id_praticien.toString())
            intent.putExtra("token", token)
            startActivityForResult(intent, 0)
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            token =  data.getStringExtra("token")
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) {
            val praticien = ServicePraticien()
            val reponsePraticien = praticien.getLesPraticiensNom(token, etNomPratien.text.toString())
            if ((reponsePraticien.Message == null) && (reponsePraticien.token != "Invalide")) {
                this.lesPraticien = reponsePraticien.lesPraticiens
                this.token = reponsePraticien.token
            } else if (reponsePraticien.token == "Invalide"){
                toast("Erreur : le token est invalide")
            } else {
                toast("Erreur : " + reponsePraticien.Erreur)
            }
        } else {
            val praticien = ServicePraticien()
            val reponsePraticien = praticien.getLesPraticiensNomType(token, position.toString(), etNomPratien.text.toString())
            if ((reponsePraticien.Message == null) && (reponsePraticien.token != "Invalide")) {
                this.lesPraticien = reponsePraticien.lesPraticiens
                this.token = reponsePraticien.token
            } else if (reponsePraticien.token == "Invalide"){
                toast("Erreur : le token est invalide")
            } else {
                toast("Erreur : " + reponsePraticien.Erreur)
            }
        }
        chargerTableau()
    }
}



