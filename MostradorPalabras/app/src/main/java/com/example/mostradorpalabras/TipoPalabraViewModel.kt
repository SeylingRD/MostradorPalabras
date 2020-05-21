package com.example.mostradorpalabras

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TipoPalabraViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: RepositorioTipoPalabra

    val allPalabras: LiveData<List<TipoPalabra>>

    init {
        val tipoPalabrasDao = PalabraRoomDatabase.getDatabase(application, viewModelScope).tipoPalabraDao()
        repository = RepositorioTipoPalabra(tipoPalabrasDao)
        allPalabras = repository.allTipoPalabras
    }

    /**
     * Lanzar una nueva rutina para insertar los datos de manera no bloqueante
     */
    fun insert(trantipoPalabra: TipoPalabra) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(trantipoPalabra)
    }

}