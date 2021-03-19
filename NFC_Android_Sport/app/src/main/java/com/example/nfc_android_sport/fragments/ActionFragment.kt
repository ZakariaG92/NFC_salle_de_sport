package com.example.nfc_android_sport.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.nfc_android_sport.R
import com.example.nfc_android_sport.api.Utility
import com.example.nfc_android_sport.model.Action
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ACTION_PARAM = "param1"
private const val ID_PARAM = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ActionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var action_parameter: String? = null
    private var id_parameter: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            action_parameter = it.getString(ACTION_PARAM)
            id_parameter = it.getString(ID_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_action, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var action_show = requireView().findViewById<TextView>(R.id.show_action)
        var url:String?=null
        if (action_parameter.toString()== Utility.ACTION.ACCES_CLUB.toString()) url="client/porte/"+id_parameter
        else  if (action_parameter.toString()== Utility.ACTION.BOISSON.toString()) url="client/boisson/"+id_parameter
        else  if (action_parameter.toString()== Utility.ACTION.COURS_COLLECTIFS.toString()) url="client/cours/"+id_parameter


            lifecycleScope.launch(Dispatchers.IO) {
                val data= getData(view,url!!);
              var  gson:Gson= Gson()
              var action:Action =gson.fromJson<Action>(data, Action::class.java)

                if (action.erreur==0) action_show.text= action.action
                else action_show.text= action.message

            }







    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id Parameter 1.
         * @param action Parameter 2.
         * @return A new instance of fragment ActionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(id: String, action: String) =
            ActionFragment().apply {
                arguments = Bundle().apply {
                    putString(ACTION_PARAM, action)
                    putString(ID_PARAM, id)
                }
            }
    }

    private suspend fun getData(view: View, url:String): String? {

        val  url = Utility.BASE_URL+ url
        val myURL = URL(url)
        return  Utility.get(myURL)

    }
}