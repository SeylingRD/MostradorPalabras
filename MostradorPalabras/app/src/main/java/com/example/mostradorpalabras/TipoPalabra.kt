package com.example.mostradorpalabras

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tipos_palabras"
)
class TipoPalabra(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "tipo") var tipo: String
)