package com.example.projettdmubunto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        //RoomService.getAyahDo().getAyahs()
                /*
        setupActionBarWithNavController(findNavController(R.id.fragment))
*/

        mynote.setOnClickListener{
          val intent=Intent(this,AyatSaved::class.java)
          startActivity(intent)
        }
        listen_to.setOnClickListener{
            val intent=Intent(this, MojawidActivity::class.java)
            startActivity(intent)
        }
        goToSearch.setOnClickListener{
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
 }
    /*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
}






