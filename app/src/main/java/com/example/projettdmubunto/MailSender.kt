package com.example.projettdmubunto

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mail_sender.*

class MailSender : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail_sender)
        //button click to get input and call sendEmail method
        sendEmailBtn.setOnClickListener{
            //get input from EditTexts and save in variables

            val recipient = recipientEt.text.toString().trim()
            val subject = subjectEt.text.toString().trim()
            var intent=intent
            val aya:String=intent.getStringExtra("aya")as String
            var message = messageEt.text.toString().trim()
            message += aya
            messageEt.setText(message)
            //method call for email intent with these inputs as parameters
            sendEmail(recipient, subject, message)
        }
    }

    @SuppressLint("LongLogTag")
    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            Toast.makeText(this, "rana nb3thi a shikh ", Toast.LENGTH_SHORT).show()
            Log.e("ouiiiiiiiiiiiiiiiii wld 3amti ","labas")

        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, "wlh mahabt a shikh ", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}

