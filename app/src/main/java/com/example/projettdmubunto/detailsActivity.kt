package com.example.projettdmubunto

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ayah.*
import kotlinx.android.synthetic.main.ayahdetails.*

class detailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayahdetails)
        gotosearch2.setOnClickListener{
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }


    }

}