package com.example.td4_exo4.Response


import com.example.td4_exo4.Model.DataRecitator
import com.google.gson.annotations.SerializedName

data class Recitator(
    @SerializedName("data")
    val data: List<DataRecitator>
)