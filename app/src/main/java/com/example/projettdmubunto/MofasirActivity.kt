package com.example.projettdmubunto

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.projettdmubunto.Interface.RetrofitService
import com.example.projettdmubunto.R
import com.example.td4_exo4.Common.TafseerRepo
import com.example.td4_exo4.Model.AyaTafseer
import com.example.td4_exo4.Model.Tafseer
import kotlinx.android.synthetic.main.activity_mofasir.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class MofasirActivity : AppCompatActivity() {
    lateinit var mService : RetrofitService
    private var idMofasir by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mofasir)
        mService=TafseerRepo.retrofitService
        getMofasir()
        val aya=intent.getSerializableExtra("aya") as Ayah
        //Toast.makeText(this,aya?.toString(),Toast.LENGTH_SHORT).show()
        idMofasir=1
        getTafseer(idMofasir,aya.idSourat as Int,aya.NumAya as Int)
    }
    private fun getTafseer(id:Int,numS:Int,numA:Int) {
        mService.getAyaTafseer(id,numS,numA).enqueue(object : Callback<AyaTafseer> {
            override fun onFailure(call: Call<AyaTafseer>, t: Throwable) {

                //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(this@MofasirActivity,"error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AyaTafseer>, response: Response<AyaTafseer>) {
                tafsir_content.text= response.body()!!.text

            }

        })

    }
    private fun getMofasir(){
        mService.getTafseer().enqueue(object : Callback<List<Tafseer>> {
            override fun onFailure(call: Call<List<Tafseer>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Tafseer>>, response: Response<List<Tafseer>>) {
                if(response.isSuccessful){
                    val data=response.body() as MutableList<Tafseer>
                    val mofasir:ArrayList<String> = arrayListOf()
                    for(i in 0..data.size-1){
                        mofasir.add(data[i].name)
                    }
                    val arrayAdapter= ArrayAdapter(this@MofasirActivity,android.R.layout.simple_spinner_item,mofasir)
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter=arrayAdapter
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val id:Int =data[position].id
                            saveMofasir.setOnClickListener {
                                val aya=intent.getSerializableExtra("aya") as Ayah
                                //Toast.makeText(this,aya?.toString(),Toast.LENGTH_SHORT).show()
                                getTafseer(id,aya.idSourat as Int,aya.NumAya as Int)
                                Toast.makeText(this@MofasirActivity,"selected"+id,Toast.LENGTH_SHORT).show()
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
    private fun saveData(id:Int) {

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putInt("INT_KEY",id)

        }.apply()
        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()
    }
}