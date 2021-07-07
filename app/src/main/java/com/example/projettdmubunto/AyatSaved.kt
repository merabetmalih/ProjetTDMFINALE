package com.example.projettdmubunto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_ayat_saved.*
import kotlinx.android.synthetic.main.searchpage.*

class AyatSaved : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat_saved)
        val ayadeo = RoomService.getDatabase(context = this@AyatSaved).getAyahSavedDo()
        var ayatsavedo=ayadeo.getAllAyahSaved()

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1 , ayatsavedo)
        ayat_list.adapter=adapter

    }
}