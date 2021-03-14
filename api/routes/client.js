let Client = require('../model/client');

// Récupérer tous les Clients (GET)
function getClients(req, res){
    console.log("get clients")
    Client.find((err, Clients) => {
        if(err){
            res.send(err)
        }

        res.send(Clients);
    });
}



// Récupérer un Client par son id (GET)
function getClient(req, res){
    console.log("**************************");
    console.log("Get clients");
    let ClientId = parseInt(req.params.id);
    console.log(ClientId);

    Client.findOne({id: ClientId}, (err, Client) =>{
        if(err){res.send(err)}
        res.json(Client);
    })
}

function getClientById(req, res){
    let Clientid = req.params.id;

    Client.find({id: Clientid}, (err, Clients) =>{
        if(err){res.send(err)}
        res.json(Clients);
    })
}

// Pour vérifier l'bonnement de l'ouverture de la porte
function ouvrirPorte(req, res){
    let Clientid = parseInt(req.params.id);

    Client.findOne({id: Clientid}, (err, Clients) =>{
        if(err){res.send(err)}
        res.json(Clients);

        let clientPack = Clients.abonnement[Clients.abonnement.length - 1].pack;
        let clientDateFin = Clients.abonnement[Clients.abonnement.length - 1].dateFin;

        var dateAuj = new Date();
        var mydate = new Date(clientDateFin);

        var difference = dateAuj - mydate; // difference in milliseconds

        //console.log(difference);

        var jsonDeRetour={};

        if (difference <= 0)
            { 
                /*
                switch (clientPack) 
                {
                    case 1:
                        console.log('Ouverture de porte');
                        break;
                    case 2:
                        console.log('Ouverture de porte/Boisson');
                        break;
                    case 3:
                        console.log('Ouverture de porte/Boisson/cours collectif');
                        break;
                }
                */

                jsonDeRetour = {
                    "action":"Porte ouverte",
                    "erreur":0,
                    "message":"null"
                };
                console.log(jsonDeRetour)
                return jsonDeRetour; 
            }
        else
            { 
                jsonDeRetour = {
                    "action":"Porte non ouverte",
                    "erreur":1,
                    "message":"abonnement expiré"
                };
                
                console.log(jsonDeRetour)
                return jsonDeRetour;     
            }           
    })
}


// Pour vérifier l'bonnement de la boisson
function prendreBoisson(req, res){
    let Clientid = parseInt(req.params.id);

    Client.findOne({id: Clientid}, (err, Clients) =>{
        if(err){res.send(err)}
        res.json(Clients);

        let clientPack = Clients.abonnement[Clients.abonnement.length - 1].pack;
        let clientDateFin = Clients.abonnement[Clients.abonnement.length - 1].dateFin;

        var dateAuj = new Date();
        var mydate = new Date(clientDateFin);

        var difference = dateAuj - mydate; // difference in milliseconds

        //console.log(difference);

        var jsonDeRetour={};

        if (difference <= 0)
            { 
                
                switch (clientPack) 
                {
                    case 1:
                        jsonDeRetour = {
                            "action":"Boisson non distribué",
                            "erreur":1,
                            "message":"Votre abonnement ne vous permet pas de bénéficier de cette offre"
                        };
                        break;
                    case 2:
                    case 3:
                        jsonDeRetour = {
                            "action":"Boisson distribué",
                            "erreur":0,
                            "message":"null"
                        };
                        break;
                }
                

                console.log(jsonDeRetour)
                return jsonDeRetour; 
            }
        else
            { 
                jsonDeRetour = {
                    "action":"Boisson non distribué",
                    "erreur":1,
                    "message":"abonnement expiré"
                };
                
                console.log(jsonDeRetour)
                return jsonDeRetour;     
            }           
    })
}

// Pour vérifier l'accées aux cours collectifs
function accesCours(req, res){
    let Clientid = parseInt(req.params.id);

    Client.findOne({id: Clientid}, (err, Clients) =>{
        if(err){res.send(err)}
        res.json(Clients);

        let clientPack = Clients.abonnement[Clients.abonnement.length - 1].pack;
        let clientDateFin = Clients.abonnement[Clients.abonnement.length - 1].dateFin;

        var dateAuj = new Date();
        var mydate = new Date(clientDateFin);

        var difference = dateAuj - mydate; // difference in milliseconds

        //console.log(difference);

        var jsonDeRetour={};

        if (difference <= 0)
            { 
                
                switch (clientPack) 
                {
                    case 1:
                        jsonDeRetour = {
                            "action":"Impossible d'accéder au cours",
                            "erreur":1,
                            "message":"Votre abonnement ne vous permet pas de bénéficier de cette offre"
                        };
                        break;
                    case 2:
                        jsonDeRetour = {
                            "action":"Impossible d'accéder au cours",
                            "erreur":1,
                            "message":"Votre abonnement ne vous permet pas de bénéficier de cette offre"
                        };
                        break;
                    case 3:
                        jsonDeRetour = {
                            "action":"accées OK",
                            "erreur":0,
                            "message":"null"
                        };
                        break;
                }
                

                console.log(jsonDeRetour)
                return jsonDeRetour; 
            }
        else
            { 
                jsonDeRetour = {
                    "action":"Impossible d'accéder au cours",
                    "erreur":1,
                    "message":"abonnement expiré"
                };
                
                console.log(jsonDeRetour)
                return jsonDeRetour;     
            }           
    })
}


// Récupérer un Client par son id (GET)
function getClientRenduTrue(req, res){
    let ClientRendu = req.params.rendu;

    Client.find({rendu: ClientRendu}, (err, Clients) =>{
        if(err){res.send(err)}
        res.json(Clients);
    })
}


function getClientParNom(req, res){
    let ClientNom = req.params.nom;

    Client.find({nom: ClientNom}, (err, Clients) =>{
        if(err){res.send(err)}
        res.json(Clients);
    })
}

// Ajout d'un Client (POST)
function postClient(req, res){
    console.log("test post :");
    let client = new Client();
    console.log(client.nom)
    client.id=req.body.id;
    client.nom=req.body.nom;
    client.prenom=req.body.prenom;
    client.dateNaissance=req.body.dateNaissance;
    client.abonnement=req.body.abonnement;


    console.log("POST Client reçu :");
    console.log(client)

    client.save( (err) => {
        if(err){
            res.send('cant post Client ', err);
        }
        res.json({ message: `${client.nom} saved!`})
    })
}

// Update d'un Client (PUT)
function updateClient(req, res) {
    console.log("UPDATE recu Client : ");
    console.log(req.body);
    Client.findByIdAndUpdate(req.body._id, req.body, {new: true}, (err, Client) => {
        if (err) {
            console.log(err);
            res.send(err)
        } else {
          res.json({message: 'updated'})
        }

      // console.log('updated ', Client)
    });

}

// suppression d'un Client (DELETE)
function deleteClient(req, res) {

    Client.findByIdAndRemove(req.params.id, (err, Client) => {
        if (err) {
            res.send(err);
        }
        res.json({message: `${Client.nom} deleted`});
    })
}



module.exports = { getClients, postClient, getClient, updateClient, deleteClient, getClientRenduTrue, getClientById, ouvrirPorte, prendreBoisson, accesCours, getClientParNom };
