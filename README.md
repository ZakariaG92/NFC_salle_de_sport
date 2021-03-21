# PROJET NFC

## Etude de cas
#
Imaginez et décrivez un cas dutilisation d’un des deux modes
ouverts du NFC.

    - Proposez une application mobile utilisant le NFC
    - Décrivez les fonctionnalités de l’application
    - Proposez une architecture globale de la plateforme imaginée

### Livrable : Document décrivant l’application

    - Description du projet
    - Description des cas d’utilisations
    - Architecture de la solution
    - Calendrier de livraison

### Consignes

    - Groupe de 2 étudiants
    - L’application peut utiliser une plateforme existante
    - L’application sera à développer dans le cadre du cours
    - Si l’application proposée est trop minimaliste
    - Il peut vous être demandé de l’enrichir
    - Un sujet peut vous être proposé


## Le projet : Salle de sport NFC
#

[Description et l'architecture de notre projet](https://docs.google.com/document/d/1nF88o9UC4qS5xnI8JMpW8W_0LqgmD653N2xx1TyGOmE/edit).

Hebergement sur [Héroku](https://warm-journey-56638.herokuapp.com/api/clients).


### Objectif réalisés

Serveur :

    Mise en place d'un serveur local Node Js
    Stockage des données dans une base de donnéé Mongo (Mongo Altas) 
    Implémentation de services :
        - Ajout et suppression de clients dans la base de données
        - Mise à jour des informations du client
        - Récupération des informations d'un client
        - Verification des accès 
                                 -> Ouverture de la porte
                                 -> Utilisation à machine boisson
                                 -> Accès cours collectifs
    

Android :  

    Lecture et écriture NFC
    Interface graphique permettant :
        - D'ajouter un clinet
        - De générer une carte abonnée
        - De donner l'accès aux
                            -> Club
                            -> Distributeur de boisson
                            -> COurs collectifs
    
