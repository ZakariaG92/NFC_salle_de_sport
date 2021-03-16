package com.example.nfc_android_sport.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.nfc_android_sport.R
import com.example.nfc_android_sport.api.Utility
import com.example.nfc_android_sport.api.Utility.BASE_URL
import com.example.nfc_android_sport.model.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddClientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddClientFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNom = requireView().findViewById<TextView>(R.id.addClientNom)
        val buttonPrenom = requireView().findViewById<TextView>(R.id.addClientPrenom)
        val buttonDate = requireView().findViewById<EditText>(R.id.addClientDate)
        val buttonSubmit = requireView().findViewById<Button>(R.id.createButtonClient)

        buttonSubmit.setOnClickListener {

          val id =  Random.nextInt(1000000)

            val sDate1 = buttonDate.text.toString();
            val date1: Date = SimpleDateFormat("dd/MM/yyyy").parse(sDate1)
            var client:Client= Client(id,buttonNom.text.toString(),
               buttonPrenom.text.toString(),date1.toString(),null);
            lifecycleScope.launch(Dispatchers.IO) {




                Utility.post(BASE_URL+"clients",client)
            }


        }





    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddClientFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddClientFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}