package com.example.projettdmubunto

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Ayah::class,History::class],version = 1)
abstract class DataBase:RoomDatabase() {



        abstract fun getAyahDo():AyahDao
        abstract fun getHistoryDo():HistoryDao

}
