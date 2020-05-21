package com.example.mostradorpalabras

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PalabraDao {
    @Query("SELECT * from palabras ORDER BY palabra ASC")
    fun getAlphabetizedWords(): LiveData<List<Palabra>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(palabra: Palabra)

    @Query("DELETE FROM palabras")
    fun deleteAll()
}