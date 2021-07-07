package com.example.projettdmubunto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.racinepage.*

class RacineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.racinepage)

        val ayahDao = RoomService.getDatabase(this).getAyahDo()
        val searchQuery=intent.getStringExtra("searchQuery")
        val ayas=ayahDao.getayahsRacine(searchQuery!!)


        val adapter = AyahAdapter(this,ayas){

            val intent = Intent(this ,detailsActivity::class.java)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(this)
        val recyclerAyah:RecyclerView=findViewById(R.id.recyclerAyah)

        recyclerAyah.layoutManager = layoutManager
        recyclerAyah.adapter = adapter

        gotosearch.setOnClickListener{
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
    }

}