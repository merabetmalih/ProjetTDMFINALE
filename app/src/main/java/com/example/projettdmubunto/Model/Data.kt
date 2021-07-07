package com.example.tdm_exo8_s5.Model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("number")
    var number: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("text")
    var text: String
)