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
import androidx.fragment.app.Fragment
import com.example.nfc_android_sport.fragments.Client2Fragment
import com.example.nfc_android_sport.fragments.WriteCardFragment
import java.io.UnsupportedEncodingException
import kotlin.experimental.and


class MainActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    //lateinit var id_user:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val client= Client2Fragment();
        val writeCardFragment= WriteCardFragment();
        changeFragment(writeCardFragment)
      /*  val readTag = findViewById<Button>(R.id.readTag);
        val writeTag = findViewById<Button>(R.id.writeTag);

        readTag.setOnClickListener {
             val intent = Intent(this, NFCReaderActivity::class.java)
             startActivity(intent);
        }

        writeTag.setOnClickListener {
            val intent = Intent(this, NFCWriterActivity::class.java)
            startActivity(intent);
        }
*/

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

fun MainActivity.changeFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        //3) on remplace le contenu du container
        replace(R.id.fragment_container, fragment)
        //4) on ajoute la transaction dans la backstack
        // addToBackStack(null)
    }.commit()
    // 5) on commit la transaction
}