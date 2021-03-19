package com.example.nfc_android_sport

import android.app.PendingIntent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nfc_android_sport.api.Utility.ACTION
import com.example.nfc_android_sport.api.Utility.FRAGMENT
import com.example.nfc_android_sport.fragments.ActionFragment
import com.example.nfc_android_sport.fragments.AddClientFragment
import com.example.nfc_android_sport.fragments.MainFragment
import com.example.nfc_android_sport.fragments.WriteCardFragment


class MainActivity : AppCompatActivity() {

    val mainFragment= MainFragment();
    val writeCardFragment= WriteCardFragment();
    val addClientFragment= AddClientFragment();


    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    //lateinit var id_user:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ChoiceCategorie = intent.extras

        if (ChoiceCategorie != null) {

          val value = ChoiceCategorie.getString("key")




            when (value) {
                /*ici le choix des bouttons pour les fragments*/
                FRAGMENT.WRITE_CARD.toString()-> { changeFragment(writeCardFragment) }
                FRAGMENT.ADD_CLIENT.toString()-> { changeFragment(addClientFragment) }

                /*ici les action pour la lecture des cartes*/
                ACTION.ACCES_CLUB.toString()->
                {
                    val  idClient = intent.extras!!.getString("id")
                    var actionFragment= ActionFragment.newInstance( idClient!!,ACTION.ACCES_CLUB.toString());
                    changeFragment(actionFragment)
                }

                ACTION.BOISSON.toString()->
                {
                    val  idClient = intent.extras!!.getString("id")
                    var actionFragment= ActionFragment.newInstance( idClient!!,ACTION.BOISSON.toString());
                    changeFragment(actionFragment)
                }

                ACTION.COURS_COLLECTIFS.toString()->
                {
                    val  idClient = intent.extras!!.getString("id")
                    var actionFragment= ActionFragment.newInstance( idClient!!,ACTION.COURS_COLLECTIFS.toString());
                    changeFragment(actionFragment)
                }


            }
        }else
        {
            changeFragment(mainFragment)
        }

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