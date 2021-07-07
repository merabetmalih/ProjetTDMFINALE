package com.example.td4_exo4.Model


import com.google.gson.annotations.SerializedName

data class DataRecitator(
    @SerializedName("englishName")
    val format: String,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("name")
    val name: String
)