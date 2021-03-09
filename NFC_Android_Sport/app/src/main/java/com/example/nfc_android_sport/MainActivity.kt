package com.example.nfc_android_sport

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.UnsupportedEncodingException
import kotlin.experimental.and


class MainActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    //lateinit var id_user:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_layout)

        val readTag = findViewById<Button>(R.id.readTag);
        val writeTag = findViewById<Button>(R.id.writeTag);

        readTag.setOnClickListener {
             val intent = Intent(this, NFCReaderActivity::class.java)
             startActivity(intent);
        }

        writeTag.setOnClickListener {
            val intent = Intent(this, NFCWriterActivity::class.java)
            startActivity(intent);
        }


        // id_user = findViewById(R.id.id_user)


        //val intent = Intent(this, NFCWriterActivity::class.java)
       // startActivity(intent);
      //  val intent = Intent(this, NFCReaderActivity::class.java)
       // startActivity(intent);


    }
    private fun needNfc() {
        Toast.makeText(this, "Ce service est disponible uniquement sur un téléphone NFC", Toast.LENGTH_LONG).show()
        finish()
    }





}