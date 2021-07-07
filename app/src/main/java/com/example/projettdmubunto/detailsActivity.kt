package com.example.projettdmubunto

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projettdmubunto.Interface.RetrofitService
import kotlinx.android.synthetic.main.ayah.*
import kotlinx.android.synthetic.main.ayahdetails.*

class detailsActivity : AppCompatActivity() {
    lateinit var mService : RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent=intent
        val aya=intent.getSerializableExtra("aya") as Ayah
        val numA=aya.NumAya
        val numS=aya.idSourat
        val idA=aya.IDAya
        getImage(numS as Int,numA as Int,this)
        setContentView(R.layout.ayahdetails)
        gotosearch2.setOnClickListener{
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }


    }
    private fun getImage(numS:Int,numA:Int,context: Context) {
        Glide.with(context).load("https://cdn.islamic.network/quran/images/"+numS+"_"+numA+".png").into(ayamushaf)
    }

}