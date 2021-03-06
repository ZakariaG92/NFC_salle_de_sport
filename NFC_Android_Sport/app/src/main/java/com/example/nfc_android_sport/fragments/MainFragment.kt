package com.example.nfc_android_sport.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.nfc_android_sport.MainActivity
import com.example.nfc_android_sport.NFCReaderActivity
import com.example.nfc_android_sport.R
import com.example.nfc_android_sport.api.Utility
import com.example.nfc_android_sport.api.Utility.FRAGMENT

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val generateCard = requireView().findViewById<Button>(R.id.generateCard)
        val addClient = requireView().findViewById<Button>(R.id.addClient)
        val accesClub = requireView().findViewById<Button>(R.id.accesClub)
        val boissons = requireView().findViewById<Button>(R.id.boisson)
        val cours = requireView().findViewById<Button>(R.id.cours)





        generateCard.setOnClickListener {
            val intent = Intent(generateCard.context, MainActivity::class.java)
            intent.putExtra("key", FRAGMENT.WRITE_CARD.toString())
            generateCard.context.startActivity(intent)

        }
        accesClub.setOnClickListener {
            val intent = Intent(accesClub.context, NFCReaderActivity::class.java)
            intent.putExtra("key", Utility.ACTION.ACCES_CLUB.toString())
            accesClub.context.startActivity(intent)

        }


        addClient.setOnClickListener {
            val intent = Intent(addClient.context, MainActivity::class.java)
            intent.putExtra("key", FRAGMENT.ADD_CLIENT.toString())
            addClient.context.startActivity(intent)

        }

        boissons.setOnClickListener {
            val intent = Intent(boissons.context, NFCReaderActivity::class.java)
            intent.putExtra("key", Utility.ACTION.BOISSON.toString())
            boissons.context.startActivity(intent)

        }

        cours.setOnClickListener {
            val intent = Intent(cours.context, NFCReaderActivity::class.java)
            intent.putExtra("key", Utility.ACTION.COURS_COLLECTIFS.toString())
            cours.context.startActivity(intent)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}