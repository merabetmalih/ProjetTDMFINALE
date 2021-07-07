package com.example.shakil.kotlinjsonexample.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit {
        if (retrofit != null) {
            retrofit=null
        }
            retrofit = Retrofit.Builder() //instance of retrofit builder class
                .baseUrl(baseUrl) //adding the base url
                .addConverterFactory(GsonConverterFactory.create())//adding the converter factory as Gson converter factory
                    //It convert Java/kotlin objects to JSON and vice versa
                .build()//build method
        return retrofit!!
    }

}