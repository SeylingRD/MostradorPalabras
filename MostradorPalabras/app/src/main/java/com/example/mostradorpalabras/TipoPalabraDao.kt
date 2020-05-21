package com.example.mostradorpalabras

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TipoPalabraDao {

    @Query("SELECT * from tipos_palabras")
    fun getAlphabetizedWords(): LiveData<List<TipoPalabra>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tipo: TipoPalabra)

    @Query("DELETE FROM tipos_palabras")
    suspend fun deleteAll()
}