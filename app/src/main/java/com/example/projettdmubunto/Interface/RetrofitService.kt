package com.example.projettdmubunto.Interface



import com.example.td4_exo4.Model.AyaTafseer
import com.example.td4_exo4.Response.Recitator
import com.example.td4_exo4.Model.Tafseer
import com.example.td4_exo4.Response.AyahDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    /**Aya translation and num de page**/
    @GET("/v1/ayah/{reference}/{edition}") // get request
    fun getAyaDetail(@Path("reference") id:String,@Path("edition") edition:String="en.asad"): Call<AyahDetail>
    /**Tafseer list endpoint**/

    @GET("/tafseer")

    fun getTafseer():Call<List<Tafseer>>

    /**Tafseer of specific aya endpoint**/

    @GET("/tafseer/{tafseer_id}/{sura_number}/{ayah_number}")
    fun getAyaTafseer(@Path("tafseer_id" )id:Int,@Path("sura_number" )sNum:Int,@Path("ayah_number" )aNum:Int):Call<AyaTafseer>

    /**Recitator  endpoint**/

    @GET("edition/format/audio")
    fun getRecitator():Call<Recitator>



}