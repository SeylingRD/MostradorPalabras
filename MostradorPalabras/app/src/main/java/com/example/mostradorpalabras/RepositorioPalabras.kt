package com.example.mostradorpalabras

import androidx.lifecycle.LiveData

class RepositorioPalabras(private val palabraDao: PalabraDao) {

    val allPalabras: LiveData<List<Palabra>> = palabraDao.getAlphabetizedWords()

    suspend fun insert(palabra: Palabra) {
        palabraDao.insert(palabra)
    }
}