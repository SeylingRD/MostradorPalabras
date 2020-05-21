package com.example.mostradorpalabras

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "palabras"
)

data class Palabra(
    @PrimaryKey(autoGenerate= true)
    var id: Int= 0,
    @ColumnInfo(name="tipo_palabra_id") var tipoPalabraId: Int,
    @ColumnInfo(name="palabra") var palabra: String,
    @ColumnInfo(name="palabraMisk") var palabraMisk: String
)
