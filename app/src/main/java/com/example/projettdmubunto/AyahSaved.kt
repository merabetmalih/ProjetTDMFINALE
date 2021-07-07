package com.example.projettdmubunto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "AyahSaved")
data class AyahSaved(
    val AyahText: String?=null,
    // val Date_Creation:Date?=null,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0
)