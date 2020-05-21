package com.example.mostradorpalabras

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PalabraViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RepositorioPalabras
    // Usar LiveData y almacenar en caché lo que devuelve getAlphabetizedWords tiene varios beneficios:
    // - Podemos poner un observador en los datos (en lugar de sondear los cambios) y solo actualizar el
    // la interfaz de usuario cuando los datos realmente cambian.
    // - El repositorio está completamente separado de la interfaz de usuario a través del ViewModel.
    val allPalabras: LiveData<List<Palabra>>

    init {
        val palabrasDao = PalabraRoomDatabase.getDatabase(application, viewModelScope).palabraDao()
        repository = RepositorioPalabras(palabrasDao)
        allPalabras = repository.allPalabras
    }

    /**
     * Lanzar una nueva rutina para insertar los datos de manera no bloqueante
     */
    fun insert(palabra: Palabra) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(palabra)
    }
}