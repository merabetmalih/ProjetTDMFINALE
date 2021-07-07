package com.example.projettdmubunto

import com.example.projettdmubunto.Interface.RetrofitService
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.projettdmubunto.R
import com.example.td4_exo4.Common.RecitatorRepo
import com.example.td4_exo4.Common.TafseerRepo
import com.example.td4_exo4.Model.DataRecitator
import com.example.td4_exo4.Model.Tafseer
import com.example.td4_exo4.Response.Recitator
import kotlinx.android.synthetic.main.activity_mofasir.*
import kotlinx.android.synthetic.main.activity_mojawid.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MojawidActivity : AppCompatActivity() {
    lateinit var mService : RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mojawid)
        mService= RecitatorRepo.retrofitService
        getRecitator()
    }
    private fun getRecitator(){
        mService.getRecitator().enqueue(object : Callback<Recitator> {
            override fun onFailure(call: Call<Recitator>, t: Throwable) {

            }

            override fun onResponse(call: Call<Recitator>, response: Response<Recitator>) {
                if(response.isSuccessful){
                    val data= response.body()!!.data as MutableList<DataRecitator>
                    val recitator:ArrayList<String> = arrayListOf()
                    for(i in 0..data.size-1){
                        recitator.add(data[i].name)
                        Log.e("recitator",data[i].name.toString())
                    }
                    val arrayAdapter= ArrayAdapter(this@MojawidActivity,android.R.layout.simple_spinner_item,recitator)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerR.adapter=arrayAdapter
                    spinnerR.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val id:String =data[position].identifier
                            saveRecitator.setOnClickListener {
                                saveData(id)
                                Toast.makeText(this@MojawidActivity,"selected"+id, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }

                }

            }

        })
    }
    private fun saveData(id:String) {

        val sharedPreferences = getSharedPreferences("sharedRecitator", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY",id)

        }.apply()
        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()
    }
}