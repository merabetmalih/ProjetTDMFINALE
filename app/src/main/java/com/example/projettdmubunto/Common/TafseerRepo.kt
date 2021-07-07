package com.example.td4_exo4.Common

import com.example.projettdmubunto.Interface.RetrofitService
import com.example.shakil.kotlinjsonexample.Retrofit.RetrofitClient

object TafseerRepo {
    private val BASE_URL = "http://api.quran-tafseer.com"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)// create method for the interface that we created
}