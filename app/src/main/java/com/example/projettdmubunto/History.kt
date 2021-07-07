package com.example.projettdmubunto


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "History")
data class History(
    val Racine: String?=null,
   // val Date_Creation:Date?=null,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0
)
