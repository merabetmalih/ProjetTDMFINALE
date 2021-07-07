package com.example.td4_exo4.Model


import com.google.gson.annotations.SerializedName

data class Tafseer(
    @SerializedName("author")
    val author: String,
    @SerializedName("book_name")
    val bookName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)