package com.example.projettdmubunto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insertRacineToHistory(history: History)
    @Query("select * from History")
    fun getAllHistory():List<History>

}