package com.example.mostradorpalabras

import androidx.lifecycle.LiveData

class RepositorioTipoPalabra (private val tipoPalabrasDao: TipoPalabraDao) {

    val allTipoPalabras: LiveData<List<TipoPalabra>> = tipoPalabrasDao.getAlphabetizedWords()

    suspend fun insert(tipoPalabra: TipoPalabra) {
        tipoPalabrasDao.insert(tipoPalabra)
    }

}