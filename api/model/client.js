let mongoose = require('mongoose');
let Schema = mongoose.Schema;


let ClientSchema = Schema({
    id: Number,
    nom: String,
    prenom: String,
    dateNaissance: Date,
    abonnement:[{ pack: Number , dateFin: Date }]

});

// C'est à travers ce modèle Mongoose qu'on pourra faire le CRUD
module.exports = mongoose.model('Client', ClientSchema);
