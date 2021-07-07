package com.example.td4_exo4.Common

import com.example.projettdmubunto.Interface.RetrofitService
import com.example.shakil.kotlinjsonexample.Retrofit.RetrofitClient

object RecitatorRepo {
    private val BASE_URL = "http://api.alquran.cloud/"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}