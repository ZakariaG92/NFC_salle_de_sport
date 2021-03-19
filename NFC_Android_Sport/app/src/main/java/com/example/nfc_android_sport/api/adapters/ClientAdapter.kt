package com.example.nfc_android_sport.api.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nfc_android_sport.R
import com.example.nfc_android_sport.model.Client

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class  ClientAdapter(private val dataset: List<Client>) :
        RecyclerView.Adapter<ClientAdapter.ViewHolder>() {


    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {


        fun bind(item: Client) {
            var id = root.findViewById<TextView>(R.id.userId)
            var nom = root.findViewById<TextView>(R.id.userName)
            var prenom = root.findViewById<TextView>(R.id.userSurName)

            val onclickClient= root.findViewById<TableRow>(R.id.tableClient)

            id.setText(item.id.toString())
            nom.setText(item.nom.toString())
            prenom.setText(item.prenom.toString())

            onclickClient.setOnClickListener {
                print("you have clicked on the user number : "+ id.text.toString())
                Toast.makeText(onclickClient.context , "you have clicked on  "+ id.text.toString(), Toast.LENGTH_SHORT).show()

                var idToWriteToCard = onclickClient.rootView.findViewById<TextView>(R.id.idToWriteToCard)
                var writeButton = onclickClient.rootView.findViewById<Button>(R.id.writeButton)
                writeButton.visibility = View.VISIBLE
                writeButton.isClickable = true;
                writeButton.setText("write  "+nom.text.toString()+"  on card")

                idToWriteToCard.setText(id.text.toString())




            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size




}