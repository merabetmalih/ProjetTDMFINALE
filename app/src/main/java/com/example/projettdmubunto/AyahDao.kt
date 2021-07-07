package com.example.projettdmubunto
import com.example.projettdmubunto.Ayah
import androidx.room.*

@Dao

interface AyahDao {
    @Query("select * from ayah LIMIT :limit")
    fun getAyahs(limit:Int):List<Ayah>

    @Query("select DISTINCT Racine from ayah ")
    fun getAllRacines():List<String>

    @Query("select * from ayah where Racine like :SearchQuery")
    fun getayahsRacine(SearchQuery:String ):List<Ayah>
    @Insert()
    fun insertAll(ayas: List<Ayah>)

}
