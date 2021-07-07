package com.example.projettdmubunto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface AyahSavedDao {

    @Insert
    fun insertAyahSaved(ayasaved: AyahSaved)
    @Query("select AyahText from AyahSaved")
    fun getAllAyahSaved():List<String>

}
