package com.example.nfc_android_sport.model


public class Client(
        val id: Int,
        var nom: String,
        val prenom: String,
        val dateNaissance: String?,
        val abonnement: List<Abonnement>?
)