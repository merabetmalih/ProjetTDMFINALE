package com.example.projettdmubunto


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ayah")
data class Ayah(
        val idRacine: Int?=null,
        val Racine: String?=null,
        val IDWord: Long?=null,
        val IDAya: String?=null,
        val idSourat: Int?=null,
        val NumAya: Int?=null,
        val ArabicWord:String?=null,
        val englishWord:String?=null,
        val textAR:String?=null,
        @PrimaryKey(autoGenerate = true)
        val id: Int=0
):Serializable{

}