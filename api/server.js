let express = require('express');
let app = express();
let bodyParser = require('body-parser');
let assignment = require('./routes/assignments');
let client = require('./routes/client');



// parse requests of content-type - application/json
app.use(bodyParser.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

// routes
require('./routes/auth.routes')(app);
require('./routes/user.routes')(app);

const db = require("./model");
const Role = db.role;

let mongoose = require('mongoose');
mongoose.Promise = global.Promise;
//mongoose.set('debug', true);

// remplacer toute cette chaine par l'URI de connexion à votre propre base dans le cloud s
const uri = 'mongodb+srv://DotNetUser:DotNetUser0@cluster0.wu4c5.mongodb.net/DbDotNetProject?retryWrites=true&w=majority';
//const uri = 'mongodb+srv://Allan:koensieg@angular.ugcwq.mongodb.net/assignments?retryWrites=true&w=majority';

const options = {
    useNewUrlParser: true,
    useUnifiedTopology: true,
    useFindAndModify: false
};

Role.estimatedDocumentCount((err, count) => {
    if (!err && count === 0) {
        new Role({
            name: "user"
        }).save(err => {
            if (err) {
                console.log("error", err);
            }

            console.log("added 'user' to roles collection");
        });

        new Role({
            name: "moderator"
        }).save(err => {
            if (err) {
                console.log("error", err);
            }

            console.log("added 'moderator' to roles collection");
        });

        new Role({
            name: "admin"
        }).save(err => {
            if (err) {
                console.log("error", err);
            }

            console.log("added 'admin' to roles collection");
        });
    }
});

mongoose.connect(uri, options)
    .then(() => {
            console.log("Connecté à la base MongoDB assignments dans le cloud !");
            console.log("at URI = " + uri);
            console.log("vérifiez with http://localhost:8010/api/assignments que cela fonctionne")
        },
        err => {
            console.log('Erreur de connexion: ', err);
        });

// Pour accepter les connexions cross-domain (CORS)
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    next();
});

// Pour les formulaires
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

let port = process.env.PORT || 8010;

// les routes
const prefix = '/api';

app.route(prefix + '/assignments')
    .get(assignment.getAssignments);

app.route(prefix + '/assignments/rendu/:rendu')
    .get(assignment.getAssignmentRenduTrue)

app.route(prefix + '/assignments/id/:id')
    .get(assignment.getAssignment)
    .delete(assignment.deleteAssignment);



app.route(prefix + '/assignments')
    .post(assignment.postAssignment)
    .put(assignment.updateAssignment);



app.route(prefix + '/clients')
    .get(client.getClients)
    .post(client.postClient)
    .put(client.updateClient);

// Client par ID
app.route(prefix + '/client/id/:id')
.get(client.getClientById)

// Client par Nom
app.route(prefix + '/client/nom/:nom')
.get(client.getClientParNom)

// Porte
app.route(prefix + '/client/porte/:id')
.get(client.ouvrirPorte)

// Boisson
app.route(prefix + '/client/boisson/:id')
.get(client.prendreBoisson)

// Accées cours
app.route(prefix + '/client/cours/:id')
.get(client.accesCours)

// On démarre le serveur
app.listen(port, "0.0.0.0");
console.log('Serveur démarré sur http://localhost:' + port);

module.exports = app;