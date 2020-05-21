package com.example.mostradorpalabras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agregar_palabra.*

class AgregarPalabra: AppCompatActivity() {
    private lateinit var editPalabraView: EditText
    private lateinit var  editPalabraView2: EditText
    private lateinit var tipoPalabraViewModel: TipoPalabraViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_palabra)
        //Llenar spinner
        cargarSpinner()
        //
        //
        editPalabraView = findViewById(R.id.etPalabra)
        editPalabraView2= findViewById(R.id.etPalabra2)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(editPalabraView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                var tipo = 1
                var movimiento = spTipoPalabra.selectedItem.toString()
                if (movimiento.equals("Español")) {
                    tipo = 1
                } else {
                    tipo = 2
                }
                val pal = editPalabraView.text.toString()
                val pal2= editPalabraView2.text.toString()


                replyIntent.putExtra("Palabra", pal)
                replyIntent.putExtra("Palabra2", pal2)
                replyIntent.putExtra("Tipo", tipo.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    fun validarDatos() {
        if (etPalabra.text.toString().isEmpty()  || etPalabra2.text.toString().isEmpty()) {
            Toast.makeText(this, "Todos los datos son requeridos",
                Toast.LENGTH_SHORT).show()
        }
        else {
        }
    }

    fun cargarSpinner(){
        var español = TipoPalabra(1, "Español")
        var miskito = TipoPalabra(2, "Miskito")
        var opcionesTipoPalabra = listOf(español.tipo, miskito.tipo)
        var adapterTipo = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            opcionesTipoPalabra
        )
        spTipoPalabra.adapter = adapterTipo

    }
}