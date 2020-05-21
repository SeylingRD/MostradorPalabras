package com.example.mostradorpalabras

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Palabra::class,TipoPalabra::class), version = 1, exportSchema = false)
abstract class PalabraRoomDatabase: RoomDatabase() {

    abstract fun palabraDao(): PalabraDao
    abstract fun tipoPalabraDao(): TipoPalabraDao

    private class palabraDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {

                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: PalabraRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PalabraRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PalabraRoomDatabase::class.java,
                    "palabras_bd"
                )
                    .addCallback(palabraDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}