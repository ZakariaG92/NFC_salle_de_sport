package com.example.nfc_android_sport.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nfc_android_sport.api.adapters.ClientAdapter
import com.example.nfc_android_sport.model.Client

import com.example.nfc_android_sport.R
import com.example.nfc_android_sport.R.*

import com.example.nfc_android_sport.api.Utility
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.net.URL

/**
 * A simple [Fragment] subclass.
 * Use the [WriteCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WriteCardFragment : Fragment() {

    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_write_card, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getClientByNameButton = view.findViewById<Button>(R.id.searchButton);
        var gson: Gson = Gson();

        getClientByNameButton.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                val nameBox= view.findViewById<TextView>(R.id.searchName);
                val data= getData(view,"client/nom/"+nameBox.text.toString());
                val client:List<Client> = gson.fromJson(data, Array<Client>::class.java).toList()

                bindData(client,view)
            }
        }




        lifecycleScope.launch(Dispatchers.IO) {
            val data= getData(view,"clients");
            val clien:List<Client> = gson.fromJson(data, Array<Client>::class.java).toList()

            bindData(clien,view)
        }





}

companion object {
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
* Use this factory method to create a new instance of
* this fragment using the provided parameters.
*
* @param param1 Parameter 1.
* @param param2 Parameter 2.
* @return A new instance of fragment testFragment.
*/
// TODO: Rename and change types and number of parameters


}
    private suspend fun getData(view: View, url:String): String? {

        val  url =Utility.BASE_URL+ url
        val myURL = URL(url)
        return  Utility.get(myURL)
        //val result = repository.listQuery()

//  bindData(result!!.articles,view)

    }

    private suspend fun bindData(result: List<Client>, view: View) {
        withContext(Dispatchers.Main) {
            val recyclerView: RecyclerView = view.findViewById(R.id.recycler_user)
            val adapterRecycler = ClientAdapter(result)
            recyclerView.hasFixedSize()
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapterRecycler
        }
    }
}