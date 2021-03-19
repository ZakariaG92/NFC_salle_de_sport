package com.example.nfc_android_sport

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.nfc_android_sport.api.Utility
import com.example.nfc_android_sport.api.Utility.ACTION
import java.io.UnsupportedEncodingException
import java.net.URL
import kotlin.experimental.and

class NFCReaderActivity : Activity() {
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    lateinit var buttonread:Button;


    var actionToDo:String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.readlayout)

        actionToDo = intent.extras!!.getString("key").toString();




        // Get default NfcAdapter and PendingIntent instances
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        // check NFC feature:
        if (nfcAdapter == null) {
            needNfc()
        }

        // single top flag avoids activity multiple instances launching
        pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
    }

    override fun onResume() {
        super.onResume()
        val adapter = nfcAdapter ?: return run {
            needNfc()
        }

        if (!adapter.isEnabled) {
            // process error NFC not activated…
            Toast.makeText(this, "Votre capteur NFC est désactivé", Toast.LENGTH_LONG).show()
            finish()
        }
        // Activer la découverte de tag en --> Android va nous envoyer directement les tags détéctés
        adapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    private fun needNfc() {
        Toast.makeText(this, "Ce service est disponible uniquement sur un téléphone NFC", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onPause() {
        super.onPause()

        // Soyons sympa en désactivant le NFC quand l'activité n'est plus visible
        nfcAdapter?.disableForegroundDispatch(this)
    }






    public override fun onNewIntent(intent: Intent) {

        buttonread= this.findViewById(R.id.button_read)
    val messageReader= readNfc(intent);

        if (messageReader != null && actionToDo!= null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id",  messageReader)
            when (actionToDo) {


                ACTION.ACCES_CLUB.toString()->
                {
                    intent.putExtra("key",  ACTION.ACCES_CLUB.toString())
                    buttonread.context.startActivity(intent)
                }

                ACTION.BOISSON.toString()->
                {
                    intent.putExtra("key",  ACTION.BOISSON.toString())
                    buttonread.context.startActivity(intent)
                }

                ACTION.COURS_COLLECTIFS.toString()->
                {
                    intent.putExtra("key",  ACTION.COURS_COLLECTIFS.toString())
                    buttonread.context.startActivity(intent)

                }

            }
        }
    }

    private suspend fun getData(view: View, url:String): String? {

        val  url = Utility.BASE_URL+ url
        val myURL = URL(url)
        return  Utility.get(myURL)
        //val result = repository.listQuery()

//  bindData(result!!.articles,view)

    }

    fun readNfc(intent: Intent):String {

        var response:String = "";
        // Get the Tag object:
        // ===================
        // retrieve the action from the received intent
        val action = intent.action
        // check the event was triggered by the tag discovery
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action || NfcAdapter.ACTION_TECH_DISCOVERED == action || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
            var message = "AUCUNE INFORMATION TROUVEE SUR LE TAG !!!"

            // get the tag object from the received intent
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

            // Get the Tag object information:
            // ===============================
            // get the UTD from the tag
            val uid = tag!!.id
            message = "Tag détecté UID : $uid"

            // get the technology list from the tag
            val technologies = tag.techList
            // bit reserved to an optional file content descriptor
            val content = tag.describeContents()
            // get NDEF content
            val ndef = Ndef.get(tag)
            // is the tag writable?
            val isWritable = ndef.isWritable
            message = if (isWritable) "$message réinscriptible" else "$message non inscriptible"

            // can the tag be locked in writing?
            val canMakeReadOnly = ndef.canMakeReadOnly()
            message = if (canMakeReadOnly) "$message, verrouillable en écriture" else "$message, non verrouillable en écriture"

            // : get NDEF records:
            // ===================
            val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            // check if the tag contains an NDEF message
            if (rawMsgs != null && rawMsgs.size != 0) {
                // instantiate a NDEF message array to get NDEF records
                val ndefMessage = arrayOfNulls<NdefMessage>(rawMsgs.size)
                // loop to get the NDEF records
                for (i in rawMsgs.indices) {
                    ndefMessage[i] = rawMsgs[i] as NdefMessage
                    for (j in ndefMessage[i]!!.records.indices) {
                        val ndefRecord = ndefMessage[i]!!.records[j]

                        // parse NDEF record as String:
                        // ============================
                        val payload = ndefRecord.payload
                        val encoding = if (payload[0] and 128.toByte() == 0.toByte()) "UTF-8" else "UTf-8"
                        val languageSize: Int = (payload[0] and 51.toByte()).toInt()
                        try {
                            val recordTxt = String(
                                payload, languageSize + 1,
                                payload.size - languageSize - 1, charset(encoding)
                            )
                            message = "$message, NDEF MESSAGE : $recordTxt"
                            response= recordTxt;
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                        }
                       // Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        print(message + "is recieved ")

                        /*                       //check NDEF record TNF:
                        //======================
                        switch(ndefRecord.getTnf()) {
                            case NdefRecord.TNF_ABSOLUTE_URI:
                                // manage NDEF record as an URI object
                                break;
                            case NdefRecord.TNF_EXTERNAL_TYPE:
                                // manage NDEF record as an URN (<domain_name>:<service_name>)
                                break;
                            case NdefRecord.TNF_MIME_MEDIA:
                                // manage NDEF record as the MIME type is:
                                // picture, video, sound, JSON, etc…
                                break;
                            case NdefRecord.TNF_WELL_KNOWN:
                                // manage NDEF record as the type is:
                                // contact (business card), phone number, email…
                                break;
                            default:
                                // manage NDEF record as text…
                        }
*/
                    }
                }
            }
        }
        return response
    };
}


