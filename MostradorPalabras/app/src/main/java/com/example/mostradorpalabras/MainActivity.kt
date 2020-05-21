package com.example.mostradorpalabras

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var palabras = mutableListOf<Palabra>()
    private lateinit var palabraViewModel: PalabraViewModel
    private val newPalabraActivityRequestCode=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PalabraListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        palabraViewModel = ViewModelProvider(this).get(PalabraViewModel::class.java)

        palabraViewModel.allPalabras.observe(this, Observer { palabras ->
            // Update the cached copy of the words in the adapter.
            palabras?.let { adapter.setPalabras(it) }

        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AgregarPalabra::class.java)
            startActivityForResult(intent, newPalabraActivityRequestCode)
        }
    }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == newPalabraActivityRequestCode && resultCode == Activity.RESULT_OK) {

                var tipo = ""
                var palabra = ""
                var palabraMisk=""

                data?.getStringExtra("Tipo")?.let {
                    tipo = it
                }
                data?.getStringExtra("Palabra")?.let {
                    palabra = it
                }
                data?.getStringExtra("Palabra2")?.let {
                   palabraMisk = it
                }


                var palabraa = Palabra(id = 0, tipoPalabraId= tipo.toInt(), palabra = palabra, palabraMisk = palabraMisk)
                palabraViewModel.insert(palabraa)

                add(palabraa)

            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        private fun add(palabr: Palabra) {
            var objeto = mutableListOf(palabr)
            palabras.addAll(objeto)

        }


    }



