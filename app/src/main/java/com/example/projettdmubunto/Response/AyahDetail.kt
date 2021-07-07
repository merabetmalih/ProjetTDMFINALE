package com.example.td4_exo4.Response


import com.example.tdm_exo8_s5.Model.Data
import com.google.gson.annotations.SerializedName

class AyahDetail(
    @SerializedName("data")
     val data: Data
) {
}

