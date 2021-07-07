package com.example.projettdmubunto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.example.projettdmubunto.Ayah
import com.example.projettdmubunto.Interface.RetrofitService
import com.example.td4_exo4.Response.AyahDetail
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.searchpage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class AyahAdapter(val context:Context, var data:List<Ayah>, val onClick :()->Unit):

    RecyclerView.Adapter<MyViewHolder>(){
    var mediaAdapter:MediaPlayer?=null
    var currentPos:Int?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return  MyViewHolder(LayoutInflater.from(context).inflate(R.layout.ayah, parent, false))

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

       holder.Ayahar.text=data[position].textAR
        holder.Ayahen.text=data[position].englishWord


        holder.Ayahar.setOnClickListener {
           onClick()
        }
        holder.Ayahen.setOnClickListener {
            onClick()
        }
        holder.sendViaMail.setOnClickListener{
            val intent=Intent(context,MailSender::class.java)
            context.startActivity(intent)
            intent.putExtra("aya",data[position].textAR)
            Toast.makeText(context, "ToSender", Toast.LENGTH_SHORT).show()
        }
        holder.tafsir.setOnClickListener{
            val intent=Intent(context,MofasirActivity::class.java)
            intent.putExtra("aya",data[position])
            context.startActivity(intent)
        }
        holder.playStart.setOnClickListener{
            if(currentPos==null || currentPos==position){
                currentPos=position
                if(mediaAdapter==null){
                    playAudioAya(data[position].IDAya as String,context){status, response ->
                        mediaAdapter=response as MediaPlayer
                        mediaAdapter!!.start()
                    }
                }else{
                    if(mediaAdapter!!.isPlaying){
                        mediaAdapter!!.pause()
                    }else{
                        mediaAdapter!!.start()
                    }
                }
            }else{
                currentPos=position
                mediaAdapter!!.stop()
                playAudioAya(data[position].IDAya as String,context){status, response ->
                    mediaAdapter=response as MediaPlayer
                    mediaAdapter!!.start()
                }
            }


        }
        holder.saveImage.setOnClickListener {
            Toast.makeText(context, "AyahSaved", Toast.LENGTH_SHORT).show()
            val ayadeo = RoomService.getDatabase(context).getAyahSavedDo()
            ayadeo.insertAyahSaved(AyahSaved(data[position].textAR))



            /*val database = Firebase.database
            val Ref = database.getReference("message")
            Ref.setValue("Hello, World!")
            val myRef = database.getReference("ayas_data").push()
            Toast.makeText(context, myRef.key, Toast.LENGTH_SHORT).show()
            myRef.child("textAr").setValue(data[position].textAR)
            myRef.setValue(data[position].IDAya).addOnCompleteListener {
                Toast.makeText(context, "SAVED", Toast.LENGTH_SHORT).show()
            }*/
        }

    }

    override fun getItemCount()=data.size
    }
    private fun playAudioAya(id:String,contect:Context,onSucessCall:(status:Boolean,response:Any)-> Unit){
        var idRecitator="ar.alafasy"
        val sharedPreferences = contect?.getSharedPreferences("sharedRecitator", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY",idRecitator)
        if(savedString.toString() !=null){
            idRecitator = savedString.toString()
        }
        var mService : RetrofitService =AyaRepo.retrofitService
        mService.getAyaDetail(id).enqueue(object : Callback<AyahDetail> {
            override fun onFailure(call: Call<AyahDetail>, t: Throwable) {

            }
            override fun onResponse(call: Call<AyahDetail>, response: Response<AyahDetail>) {
                val number:Int=response.body()!!.data.number
                playAudio("https://cdn.islamic.network/quran/audio/64/"+idRecitator+"/"+number+".mp3"){status, response ->
                    onSucessCall(true,response)
                }
                Log.e("request", number.toString())
            }
        })
    }

    private fun playAudio(url:String,onSucessCall:(status:Boolean,response:Any)-> Unit){
        val mediaPlayer= MediaPlayer()
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                onSucessCall(true,mediaPlayer)
                /*detail_audio_start_btn.setOnClickListener {
                    if(mediaPlayer!=null){
                        if(mediaPlayer!!.isPlaying){
                            mediaPlayer!!.pause()
                        }else{
                            mediaPlayer!!.start()
                        }
                    }
                }*/
            }

        }
        catch (e: IOException){
            e.printStackTrace()
        }
    }

class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
    val sendViaMail=view.findViewById<ImageView>(R.id.sendViaMail)as ImageView
    val Ayahar=view.findViewById<TextView>(R.id.ayahAr) as TextView
    val Ayahen=view.findViewById<TextView>(R.id.ayahen) as TextView
    val saveImage  = view.findViewById<ImageView>(R.id.imageView3) as ImageView
    val tafsir=view.findViewById<ImageView>(R.id.tafsir_id_image) as  ImageView
    val playStart=view.findViewById<ImageView>(R.id.imageView4) as ImageView
}
const val TAG= "AYAH ADAPTER"

