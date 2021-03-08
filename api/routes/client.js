let Client = require('../model/client');

// Récupérer tous les Clients (GET)
function getClients(req, res){
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


// Récupérer un Client par son id (GET)
function getClientRenduTrue(req, res){
    let ClientRendu = req.params.rendu;

    Client.find({rendu: ClientRendu}, (err, Clients) =>{
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



module.exports = { getClients, postClient, getClient, updateClient, deleteClient, getClientRenduTrue, getClientById };
