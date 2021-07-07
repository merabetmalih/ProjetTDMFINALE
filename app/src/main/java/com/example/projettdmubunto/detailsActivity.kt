package com.example.projettdmubunto

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projettdmubunto.Interface.RetrofitService
import com.example.td4_exo4.Response.AyahDetail
import com.example.tdm_exo8_s5.Model.Data
import kotlinx.android.synthetic.main.ayah.*
import kotlinx.android.synthetic.main.ayahdetails.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detailsActivity : AppCompatActivity() {
    lateinit var mService : RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayahdetails)
        gotosearch2.setOnClickListener{
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
        mService = AyaRepo.retrofitService
        var intent=intent
        val aya=intent.getSerializableExtra("ayah") as Ayah
        val numA=aya.NumAya
        val numS=aya.idSourat
        val idA=aya.IDAya
        Toast.makeText(this,"numA"+numA,Toast.LENGTH_SHORT).show()
        Log.e("id",numA.toString())
        Log.e("surah",numS.toString())
        getImage(numS as Int,numA as Int,this)
        getTranslation(idA!!)
        slideBack.setOnClickListener {
            if (numA as Int>2){
                getImage(numS as Int,numA as Int -1,this)
            }
        }
        slideNext.setOnClickListener {
            if (numA as Int>2){
                getImage(numS as Int,numA as Int +1,this)
            }
        }



    }
    private fun getImage(numS:Int,numA:Int,context: Context) {
        Glide.with(context).load("https://cdn.islamic.network/quran/images/"+numS+"_"+numA+".png").into(ayamushaf)
    }
    private fun getTranslation(id:String) {
        mService.getAyaDetail(id).enqueue(object : Callback<AyahDetail> {
            override fun onFailure(call: Call<AyahDetail>, t: Throwable) {

                //To change body of created functions use File | Settings | File Templates.
                // Toast.makeText(activity as MainActivity,"error",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AyahDetail>, response: Response<AyahDetail>) {
                traduction.text= response.body()!!.data.text
            }

        })
    }

}